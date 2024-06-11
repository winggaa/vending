package com.gd.vending.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.vending.dto.Vending;

@Mapper
public interface VendingEmpMapper {
	List<Vending> selectOne(String drink);
	void updateDrink(String drink , String amount , String price);
	void deleteDrink(String drink);
	void insertDrink(String drink, int amount , int price);
}
