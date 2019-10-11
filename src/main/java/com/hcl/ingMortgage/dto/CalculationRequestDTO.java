package com.hcl.ingMortgage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculationRequestDTO {

	private Integer mortgageId;
	private Double mortgageOutstanding;
	private Double rateOfInterest;
	private Double emiAmount;
	private Integer tenure;
}
