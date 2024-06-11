package com.gd.vending.controller;

import java.util.List;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.vending.dto.Vending;
import com.gd.vending.mapper.VendingMapper;
import com.gd.vending.service.VendingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class VendingController {
	@Autowired
	VendingService vendingService;
	@Autowired
	VendingMapper vendingMapper;
	
	
	@GetMapping("/Vending")
	public String drinkList(Model model) {
				
		model.addAttribute("vending", vendingService.selectDrink());
		log.debug("리스트 테스트"+vendingService.selectDrink());
		return "Vending";
	}
	
	
			// 	ajax 호출 테스트
			@RequestMapping(value = "/Vending", method = {RequestMethod.POST})
			//@PostMapping("/Vending")
			@ResponseBody // << json 으로 변환해서 반환시켜줌
			public List<Vending>  ajaxTest(Model model , @RequestParam("name") String  name) {
				System.out.println("받은 이름: " + name);
				
				vendingMapper.drinkSale(name);
				List<Vending> list = vendingMapper.selectDrink();
			    log.debug("데이터확인"+list);
//				model.addAttribute("vending", vendingMapper.selectDrink());
//				log.debug("리스트" + vendingMapper.selectDrink());
//				ModelAndView mv = new ModelAndView();
//				mv.addObject("testJson", "ajaxTest");
//				mv.setViewName("/view/Vending");
				return list; 
			}
	
}
