package com.hcl.ingMortgage.util;

import org.springframework.stereotype.Component;

import com.hcl.ingMortgage.dto.CalculationRequestDTO;
import com.hcl.ingMortgage.dto.CalculationResponseDto;

@Component
public class MortgageUtility {

	public CalculationResponseDto emiCalculation(CalculationRequestDTO calculationRequestDTO) {
		
		Double pamt =calculationRequestDTO.getMortgageOutstanding();
		Double rate =calculationRequestDTO.getRateOfInterest();
		Integer month =calculationRequestDTO.getTenure();
		
		 Double monthlyInterestRatio = (rate/100)/12;
	//	  Double monthlyInterest = (monthlyInterestRatio*pamt);
		  Double max = Math.pow((1+monthlyInterestRatio),month);
		  
		  double min = max -1; double sp = max / min;
		  Double emi = ((pamt * monthlyInterestRatio) * sp);
		  
		Double finalResult = Math.round(emi * 100.0)/100.0;
		
		CalculationResponseDto calculationResponseDto = new CalculationResponseDto();
		calculationResponseDto.setEmiAmount(finalResult);
		calculationResponseDto.setMortgageId(calculationRequestDTO.getMortgageId());
		calculationResponseDto.setTenure(calculationRequestDTO.getTenure());
		return calculationResponseDto;
		
	}
}
