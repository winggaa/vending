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
	
	// 음료수 인서트
	public void insertDrink(String drink , int price , int amount) {
		vendingEmpMapper.insertDrink(drink, amount, price);
	}
	
	//  파라미터로 들어온 음료수의 상태 검색
	public List<Vending> selectOne(String drink) {
	List<Vending> vending = vendingEmpMapper.selectOne(drink);
	return vending;
	}
	
	// 파라미터 음료수의 재고 , 가격 수정 
	public void updateOne(String drink , String amount , String price) {
		
		vendingEmpMapper.updateDrink(drink , amount , price);
		
	}
	
	// 파라미터 음료수 삭제
	public void delete(String drink) {
		vendingEmpMapper.deleteDrink(drink);
				
	}
}
