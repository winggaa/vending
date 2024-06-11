package com.gd.vending.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.vending.dto.Vending;
import com.gd.vending.mapper.VendingMapper;

import lombok.extern.slf4j.Slf4j;


@Service
public class VendingService {
	@Autowired
	VendingMapper vendingMapper;
	
	public  List<Vending> selectDrink() {
		List<Vending> vending = vendingMapper.selectDrink();
		return vending;	
	}
	
	
}
