package com.hcl.ingMortgage.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.hcl.ingMortgage.dto.MortgageListResponseDto;
import com.hcl.ingMortgage.service.MortgageService;

@RunWith(MockitoJUnitRunner.class)
public class MortgageControllerTest {


	@Mock 
	MortgageService mortgageService;
	@InjectMocks
	MortgageController mortgageController;
	
	@Test
	public void testGetUserMortgageList() {
		List<MortgageListResponseDto> ListOfMortgageListResponseDto = new ArrayList<>();
		MortgageListResponseDto mortgageListResponseDto = new MortgageListResponseDto();
		mortgageListResponseDto.setEmiAmount(12000.0);
		mortgageListResponseDto.setEmiDate(12/12/1222);
		mortgageListResponseDto.setEndDate(LocalDate.now());
		mortgageListResponseDto.setInterestRate(2.2);
		mortgageListResponseDto.setMortgageAmount(2300.0);
		mortgageListResponseDto.setMortgageId(1);
		mortgageListResponseDto.setMortgageOutstanding(1200.0);
		mortgageListResponseDto.setMortgageRepaid(120.0);
		mortgageListResponseDto.setStartDate(LocalDate.now());
		mortgageListResponseDto.setTenure(12);
		mortgageListResponseDto.setUserId(1);
		
		ListOfMortgageListResponseDto.add(mortgageListResponseDto);
		
		Mockito.when(mortgageService.fetchUserMortgageList(Mockito.anyInt())).thenReturn(ListOfMortgageListResponseDto);
		ResponseEntity<List<MortgageListResponseDto>> actual = mortgageController.getUserMortgageList(1);
		
		assertNotNull(actual);
		}

}
