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
import com.hcl.ingMortgage.dto.LoginRequest;
import com.hcl.ingMortgage.dto.LoginResponse;
import com.hcl.ingMortgage.service.LoginService;

/**
 * 
 * @author sharath vemperala
 *
 */

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(IngMortgageApplication.class);

	@Autowired
	LoginService loginService;

	/**
	 * 
	 * @param login credentials
	 * @return LoginResponse
	 */

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		logger.info(":: Enter into LoginController--------::login()");

		return new ResponseEntity<LoginResponse>(loginService.authenticate(loginRequest), HttpStatus.CREATED);

	}

}
