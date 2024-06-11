package com.gd.vending.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vending {
	
	private int No;
	private String drink;
	private int amount;
	private int price;
	
}
