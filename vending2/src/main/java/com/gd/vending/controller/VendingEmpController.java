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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class VendingEmpController {
	@Autowired 
	VendingMapper vendingMapper;
	@Autowired 
	VendingEmpService vendingEmpService;
	
	@GetMapping("/VendingEmp")
	public String drinkList(Model model) {
		// list 불러옴
		model.addAttribute("vending", vendingMapper.selectDrink());
		log.debug("리스트 테스트"+vendingMapper.selectDrink());
		return "VendingEmp";
	}

	//@RequestMapping(value = "/VendingOne", method = {RequestMethod.GET})
	@GetMapping("/VendingOne")
	public String  OneList(Model model , @RequestParam("drink") String drink) {
		System.out.println(drink);
		List<Vending> list = vendingEmpService.selectOne(drink);
		log.debug("test" + list);
		model.addAttribute("one", list);
		
		return "VendingOne"; 
	}
	
	@PostMapping("/VendingOne")
	public String OneUpdate(@RequestParam("drink")String drink , @RequestParam("price") String price ,@RequestParam("amount") String amount) {
		System.out.println(drink + price + amount +"test" );
		
		vendingEmpService.updateOne(drink,amount,price);
		return "redirect:/VendingEmp";
	}
	@PostMapping("/vendingDelete")
	public String abc (@RequestParam("drink")String drink) {
		vendingEmpService.delete(drink);
		return "redirect:/VendingEmp";
	}
	@PostMapping("/drinkCreate")
	public String postMethodName(@RequestParam("drink")String drink , @RequestParam("price") int price ,@RequestParam("amount") int amount) {
		
		vendingEmpService.insertDrink(drink, price, amount);
		return "redirect:/VendingEmp";
	}
	
	
	
	
}
