package com.gd.vending.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RequestVending {
	
	@NotNull
	private int No;
	
	@NotBlank
	private String drink;
	
	@Min(0)
	
	@NotEmpty
	private String amount;
	
	@Min(0)
	@Max(99)
	@NotEmpty(message="가격은 필수입니다. 값을 입력하세요.")
	private String price;
}
