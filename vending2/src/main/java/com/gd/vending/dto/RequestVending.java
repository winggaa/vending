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
	
	
	private Integer No;
	
	@NotBlank
	private String drink;
	
	@Min(0)
	private Integer amount;
	
	@Min(value = 0 , message="최소값은 0입니다")
	@Max(value = 100 , message="가격의 최대값은 100입니다")
	/* @NotEmpty(message="가격은 필수입니다. 값을 입력하세요.") */
	private Integer price;
}
