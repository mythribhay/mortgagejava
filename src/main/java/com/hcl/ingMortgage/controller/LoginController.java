package com.hcl.ingMortgage.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ingMortgage.dto.LoginRequest;
import com.hcl.ingMortgage.dto.LoginResponse;
import com.hcl.ingMortgage.service.LoginService;



@RestController
@Scope("session")
public class LoginController {
	@Autowired
	LoginService loginService;
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest ){
		return new ResponseEntity<LoginResponse>(loginService.authenticate(loginRequest), HttpStatus.CREATED);
		
	}

}
