package com.hcl.ingMortgage.service;

import java.util.List;

import com.hcl.ingMortgage.dto.MortgageListResponseDto;

public interface MortgageService {
	
	List<MortgageListResponseDto> fetchUserMortgageList(Integer userId);
	
	

}
