package com.hcl.ingMortgage.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ingMortgage.dto.CalculationRequestDTO;
import com.hcl.ingMortgage.dto.CalculationResponseDto;

@RunWith(MockitoJUnitRunner.class)
public class MortgageUtilityTest {

	@InjectMocks
	MortgageUtility mortgageUtility;
	
	@Test
	public void testEmiCalculation() {
		CalculationRequestDTO calculationRequestDTO = new CalculationRequestDTO();
		calculationRequestDTO.setMortgageOutstanding(2000.0);
		calculationRequestDTO.setRateOfInterest(2.2);
		calculationRequestDTO.setTenure(12);
		
		Double pamt = calculationRequestDTO.getMortgageOutstanding();
		Double rate = calculationRequestDTO.getRateOfInterest();
		Integer month =calculationRequestDTO.getTenure();
		
		Double monthlyInterestRatio = (rate/100)/12;
		Double max = Math.pow((1+monthlyInterestRatio),month);

		double min = max -1; double sp = max / min;
		Double emi = ((pamt * monthlyInterestRatio) * sp);

		Double finalResult = Math.round(emi * 100.0)/100.0;

		CalculationResponseDto calculationResponseDto = new CalculationResponseDto();
		calculationResponseDto.setEmiAmount(finalResult);
		calculationResponseDto.setMortgageId(calculationRequestDTO.getMortgageId());
		calculationResponseDto.setTenure(calculationRequestDTO.getTenure());
		
		CalculationResponseDto actual = mortgageUtility.emiCalculation(calculationRequestDTO);
				assertNotNull(actual);
		}

}
