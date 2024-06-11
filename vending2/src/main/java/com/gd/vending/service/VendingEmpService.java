package com.gd.vending.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.vending.dto.Vending;
import com.gd.vending.mapper.VendingEmpMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VendingEmpService {
	
	@Autowired
	VendingEmpMapper vendingEmpMapper;
	
	public void insertDrink(String drink , int price , int amount) {
		vendingEmpMapper.insertDrink(drink, amount, price);
	}
	
	public List<Vending> selectOne(String drink) {
		
	List<Vending> vending = vendingEmpMapper.selectOne(drink);
	return vending;
	}
	
	public void updateOne(String drink , String amount , String price) {
		
		vendingEmpMapper.updateDrink(drink , amount , price);
		
	}
	public void delete(String drink) {
		vendingEmpMapper.deleteDrink(drink);
				
	}
}
