package com.hcl.ingMortgage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculationResponseDto {
	
	private Integer mortgageId;
	private Double emiAmount;
	private Integer tenure;

}
