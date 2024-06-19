package com.gd.vending.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gd.vending.dto.RequestVending;
import com.gd.vending.dto.Vending;
import com.gd.vending.service.VendingEmpService;
import com.gd.vending.service.VendingService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class VendingEmpController {
	@Autowired 
	VendingService vendingService;
	@Autowired 
	VendingEmpService vendingEmpService;
	
	@GetMapping("/VendingEmp")
	public String drinkList(Model model) {
		
		model.addAttribute("vending", vendingService.selectDrink());
		log.debug("리스트 테스트"+vendingService.selectDrink());
		return "VendingEmp";
	}

	// 음료수 상세보기
	//@RequestMapping(value = "/VendingOne", method = {RequestMethod.GET})
	@GetMapping("/VendingOne")
	public String  drinkOneList(Model model , @RequestParam("drink") String drink) {
		System.out.println(drink);
		List<Vending> list = vendingEmpService.selectOne(drink);
		log.debug("test" + list);
		model.addAttribute("one", list);		
		return "VendingOne"; 
	}
	
	
	
	
	
	// 음료수 가격 + 재고 수정
	@PostMapping("/VendingOne")
	public String drinkUpdate(@ModelAttribute @Valid RequestVending requestVending , BindingResult bindingResult, Model model){
	//public String drinkUpdate(@RequestParam("drink")String drink , @RequestParam("price") int price ,@RequestParam("amount") int amount) {
		System.out.println(requestVending);
		String drink = requestVending.getDrink();
		int price =	(int) requestVending.getPrice();
		int	amount =(int) requestVending.getAmount();
		System.out.println(drink+price+amount);
		
		if (bindingResult.hasErrors()) {
			bindingResult.rejectValue("price", "test");
			List<Vending> list = vendingEmpService.selectOne(requestVending.getDrink());
			
			System.out.println("test1"+bindingResult.getFieldError());
			
			// dto messege 가져오기 
			String err = bindingResult.getFieldError().getDefaultMessage();
			model.addAttribute("one", list);
			model.addAttribute("error", err);
			//System.out.println(requestVending);
			return "VendingOne";
		}
		
		
		vendingEmpService.updateDrink(drink,amount,price);
		return "redirect:/Vending";	
	}
	
	
	
	
	
	
	
	
	// 판매중 음료수 삭제 
	@PostMapping("/vendingDelete")
	public String deleteDrink (@RequestParam("drink")String drink) {
		vendingEmpService.delete(drink);
		return "redirect:/VendingEmp";
	}
	
	// 새로운 음료수 생성
	@PostMapping("/drinkCreate")
	public String createDrink(@RequestParam("drink")String drink , @RequestParam("price") int price ,@RequestParam("amount") int amount) {
		vendingEmpService.insertDrink(drink, price, amount);
		return "redirect:/VendingEmp";
	}
				// 예외를 잡아서 메소드 실행시킴 속성안에 error message 내포   
				/*
				 * @ExceptionHandler(MethodArgumentNotValidException.class) public ModelAndView
				 * handleValidationErrors(MethodArgumentNotValidException ex) { List<String>
				 * errors = new ArrayList<>(); for (FieldError error :
				 * ex.getBindingResult().getFieldErrors()) {
				 * errors.add(error.getDefaultMessage()); }
				 * System.out.println("test:::::::::"+ex.getHeaders());
				 * System.out.println("test:::::::::"+ex.getBody()); ModelAndView modelAndView =
				 * new ModelAndView();
				 * modelAndView.addObject("vending",vendingService.selectDrink());
				 * modelAndView.addObject("errors", errors); // 오류 데이터를 모델에 추가
				 * modelAndView.setViewName("VendingEmp"); // 뷰 이름 설정
				 * 
				 * return modelAndView; }
				 */
	
	
}
