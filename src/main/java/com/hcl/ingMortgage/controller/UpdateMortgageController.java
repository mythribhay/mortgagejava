package com.hcl.ingMortgage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ingMortgage.IngMortgageApplication;
import com.hcl.ingMortgage.dto.UpdateMortgageDto;
import com.hcl.ingMortgage.dto.UpdateMortgageResponse;
import com.hcl.ingMortgage.service.MortgageServiceShedulde;

/**
 * 
 * @author sharath vemperala
 *
 */

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class UpdateMortgageController {

	private static final Logger logger = LoggerFactory.getLogger(IngMortgageApplication.class);

	@Autowired
	MortgageServiceShedulde mortgageServiceShedulde;

	/**
	 * 
	 * @param login UpdateMortgageDto
	 * @return UpdateMortgageResponse
	 */

	@PutMapping("/update")
	public ResponseEntity<UpdateMortgageResponse> login(@RequestBody UpdateMortgageDto updateRequest) {
		logger.info(":: Enter into LoginController--------::login()");

		return new ResponseEntity<UpdateMortgageResponse>(mortgageServiceShedulde.updateMortgage(updateRequest),
				HttpStatus.CREATED);

	}
}
