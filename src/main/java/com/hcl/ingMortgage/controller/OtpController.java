package com.hcl.ingMortgage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ingMortgage.IngMortgageApplication;
import com.hcl.ingMortgage.dto.OptRequestDto;
import com.hcl.ingMortgage.dto.OtpResponseDto;
import com.hcl.ingMortgage.exception.InvalidCredentialsException;
import com.hcl.ingMortgage.service.OtpService;

/**
 * 
 * @author sharath vemperala
 *
 */

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class OtpController {

	private static final Logger logger = LoggerFactory.getLogger(IngMortgageApplication.class);

	@Autowired
	OtpService otpService;

	/**
	 * 
	 * @param login credentials
	 * @return LoginResponse
	 * @throws InvalidCredentialsException
	 */

	@PostMapping("/otpnotification")
	public ResponseEntity<OtpResponseDto> sendOtp(@RequestBody OptRequestDto optRequestDto)
			throws InvalidCredentialsException {
		logger.info(":: Enter into otpController--------::sendOtp()");

		return new ResponseEntity<OtpResponseDto>(otpService.sendOtp(optRequestDto), HttpStatus.OK);

	}

}
