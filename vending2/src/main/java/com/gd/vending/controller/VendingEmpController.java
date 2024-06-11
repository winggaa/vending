package com.gd.vending.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.openssl.pem_password_cb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.gd.vending.dto.Vending;
import com.gd.vending.mapper.VendingMapper;
import com.gd.vending.service.VendingEmpService;
import com.gd.vending.service.VendingService;

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
		// list 불러옴
		
		// test service로 교체필
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
	
	// 음료수 수정
	@PostMapping("/VendingOne")
	public String drinkUpdate(@RequestParam("drink")String drink , @RequestParam("price") String price ,@RequestParam("amount") String amount) {
		System.out.println(drink + price + amount +"test" );
		
		vendingEmpService.updateOne(drink,amount,price);
		return "redirect:/VendingEmp";
	}
	
	// 음료수 삭제 
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
	
	
	
	
}
