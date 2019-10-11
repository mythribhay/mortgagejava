package com.hcl.ingMortgage.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ingMortgage.dto.MortgageListResponseDto;
import com.hcl.ingMortgage.entity.Mortgage;
import com.hcl.ingMortgage.exception.MortgageNotFoundException;
import com.hcl.ingMortgage.repository.MortgageRepository;

@RunWith(MockitoJUnitRunner.class)
public class MortgageServiceImplTest {

	@Mock
	MortgageRepository mortgageRepository; 
	
	@InjectMocks
	MortgageServiceImpl mortgageServiceImpl;
	
	@Test
	public void testFetchUserMortgageList() {
		
		List<Mortgage> listOfMortgage = new ArrayList<>();
		Mortgage mortgage = new Mortgage();
		mortgage.setEmiAmount(1200.0);
		mortgage.setEmiDate(12);
		mortgage.setInterestRate(2.0);
		mortgage.setMortgageAmount(12.0);
		mortgage.setMortgageId(1);
		mortgage.setMortgageOutstanding(12345.0);
		mortgage.setMortgageRepaid(123.0);
		mortgage.setTenure(12);
		mortgage.setUserId(1);
		
		listOfMortgage.add(mortgage);
		Optional<List<Mortgage>> optionalMortgage=Optional.of(listOfMortgage);
		Mockito.when(mortgageRepository.findAllByUserId(Mockito.anyInt())).thenReturn(optionalMortgage);
		
		if(optionalMortgage.isPresent()) {
			List<Mortgage> mortgageList=optionalMortgage.get();
			List<MortgageListResponseDto> mortgageResponseList=new ArrayList<>();
			mortgageList.forEach(mortgageObj->{

				MortgageListResponseDto mortgageListResponseDto = new MortgageListResponseDto();
				mortgageListResponseDto.setEmiAmount(mortgageObj.getEmiAmount());
				mortgageListResponseDto.setEmiDate(mortgageObj.getEmiDate());
				mortgageListResponseDto.setInterestRate(mortgageObj.getInterestRate());
				mortgageListResponseDto.setMortgageAmount(mortgageObj.getMortgageAmount());
				mortgageListResponseDto.setMortgageId(mortgageObj.getUserId());
				mortgageListResponseDto.setMortgageOutstanding(mortgageObj.getMortgageOutstanding());
				mortgageListResponseDto.setMortgageRepaid(123.0);
				mortgageListResponseDto.setStartDate(LocalDate.now());
				mortgageListResponseDto.setTenure(12);
				mortgageListResponseDto.setUserId(mortgageObj.getUserId());
				
				mortgageResponseList.add(mortgageListResponseDto); });

			List<MortgageListResponseDto> actual = mortgageServiceImpl.fetchUserMortgageList(1);
			assertNotNull(actual);
	}else {
		throw new MortgageNotFoundException("No mortgage found");
	}
	}

}
