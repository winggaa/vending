package com.gd.vending.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.vending.dto.Vending;

@Mapper
public interface VendingMapper {
	
	 List<Vending> selectDrink();
	 void drinkSale(String drink);
}
