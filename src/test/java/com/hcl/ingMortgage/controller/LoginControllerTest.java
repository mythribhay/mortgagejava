package com.hcl.ingMortgage.controller;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ingMortgage.dto.LoginRequest;
import com.hcl.ingMortgage.dto.LoginResponse;
import com.hcl.ingMortgage.service.LoginService;




@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
	
	@Mock
	LoginService loginService;
	
	@InjectMocks
	LoginController loginController;

	@Test
	public void testLogin() {
		LoginRequest loginRequest = new LoginRequest();
		LoginResponse loginResponse = new LoginResponse();
	}

}
