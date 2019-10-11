package com.hcl.ingMortgage.service;




import com.hcl.ingMortgage.dto.EmiDeductResponse;
import com.hcl.ingMortgage.dto.UpdateMortgageDto;
import com.hcl.ingMortgage.dto.UpdateMortgageResponse;


public interface MortgageServiceShedulde {
	
	public EmiDeductResponse payEmi();
	public UpdateMortgageResponse updateMortgage(UpdateMortgageDto updateResponse);

}
