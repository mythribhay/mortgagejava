package com.hcl.ingMortgage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ingMortgage.controller.MortgageController;
import com.hcl.ingMortgage.dto.MortgageListResponseDto;
import com.hcl.ingMortgage.entity.Mortgage;
import com.hcl.ingMortgage.exception.MortgageNotFoundException;
import com.hcl.ingMortgage.repository.MortgageRepository;

@Service
public class MortgageServiceImpl implements MortgageService {

	private static final Logger logger = LoggerFactory.getLogger(MortgageService.class);

	
	@Autowired
	MortgageRepository mortgageRepository; 
	
	/**
	 * 
	 * @param userId 
	 * @return MortgageListResponseDto- mortgage details related to that userId
	 */
	@Override
	public List<MortgageListResponseDto> fetchUserMortgageList(Integer userId) {
		
		Optional<List<Mortgage>> listOfMortgage = mortgageRepository.findAllByUserId(userId);
		if(listOfMortgage.isPresent()) {
			List<Mortgage> mortgageList=listOfMortgage.get();
			List<MortgageListResponseDto> mortgageResponseList=new ArrayList<>();
			mortgageList.forEach(mortgage->{
				logger.info(":: valid loan by user-----::={}",mortgage.getMortgageId());
				MortgageListResponseDto mortgageListResponseDto=new MortgageListResponseDto();
				mortgageListResponseDto.setUserId(mortgage.getUserId());
				mortgageListResponseDto.setEmiAmount(mortgage.getEmiAmount());
				mortgageListResponseDto.setEmiDate(mortgage.getEmiDate());
				mortgageListResponseDto.setEndDate(mortgage.getEmiEndDate());
				mortgageListResponseDto.setInterestRate(mortgage.getInterestRate());
				mortgageListResponseDto.setStartDate(mortgage.getEmiStartDate());
				mortgageListResponseDto.setMortgageOutstanding(mortgage.getMortgageOutstanding());
				mortgageListResponseDto.setMortgageId(mortgage.getMortgageId());
				mortgageListResponseDto.setMortgageAmount(mortgage.getMortgageAmount());
				mortgageListResponseDto.setMortgageRepaid(mortgage.getMortgageRepaid());
				mortgageListResponseDto.setTenure(mortgage.getTenure());
				
				mortgageResponseList.add(mortgageListResponseDto); });

			Collections.sort(mortgageResponseList, new SortByDateComparator());
		return mortgageResponseList;
	}else {
		throw new MortgageNotFoundException("No mortgage found");
	}
	}
	
	

}
