package com.gd.vending.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.vending.dto.Vending;
import com.gd.vending.mapper.VendingEmpMapper;
import com.gd.vending.mapper.VendingMapper;


@Service
public class VendingService {
	@Autowired
	VendingMapper vendingMapper;
	
	@Autowired
	VendingEmpMapper vendingEmpMapper;
	
	// 음료수 리스트 검색
	public  List<Vending> selectDrink() {
		List<Vending> vending = vendingMapper.selectDrink();
		return vending;	
	}
	
	// 음료수 판매  재고 -1 
	public void drinkSale (String name) { 	
		vendingEmpMapper.updateDrink(name,0,0);																											
	}
}
