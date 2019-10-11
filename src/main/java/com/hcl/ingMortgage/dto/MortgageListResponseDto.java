package com.hcl.ingMortgage.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MortgageListResponseDto {

	private Integer userId;
	private Integer mortgageId;
	private Double mortgageAmount;
	private Double interestRate;
	private Double mortgageRepaid;
	private Double mortgageOutstanding;
	private Double emiAmount;
	private Integer emiDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer tenure;
}
