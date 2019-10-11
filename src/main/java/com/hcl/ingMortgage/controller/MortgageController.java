package com.hcl.ingMortgage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ingMortgage.dto.CalculationRequestDTO;
import com.hcl.ingMortgage.dto.CalculationResponseDto;
import com.hcl.ingMortgage.dto.MortgageListResponseDto;
import com.hcl.ingMortgage.dto.UserResponseDto;
import com.hcl.ingMortgage.service.MortgageService;
import com.hcl.ingMortgage.service.UserService;
import com.hcl.ingMortgage.util.MortgageUtility;

/**
 * 
 * @author Manisha Yadav
 *
 */

@RestController
@RequestMapping("/mortgages")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class MortgageController {

	@Autowired
	MortgageUtility mortgageUtility;

	@Autowired 
	MortgageService mortgageService;

	private static final Logger logger = LoggerFactory.getLogger(MortgageController.class);

	@GetMapping("/{userId}") 
	public ResponseEntity<List<MortgageListResponseDto>> getUserMortgageList(@PathVariable Integer userId){

		logger.info(":: Enter into MortgageController--------::getUserMortgageList()");
		return new ResponseEntity<List<MortgageListResponseDto>>(mortgageService.fetchUserMortgageList(userId),HttpStatus.OK);
	}
	 

	@PostMapping("/emi") 
	public ResponseEntity<CalculationResponseDto> calculateTenureAndAmount(@RequestBody CalculationRequestDTO calculationRequestDTO){

		logger.info(":: Enter into MortgageController--------::getUserMortgageList()");
		return new ResponseEntity<CalculationResponseDto>(mortgageUtility.emiCalculation(calculationRequestDTO),HttpStatus.OK);
	}
}
