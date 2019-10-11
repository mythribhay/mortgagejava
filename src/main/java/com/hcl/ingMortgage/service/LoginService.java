package com.hcl.ingMortgage.service;

import com.hcl.ingMortgage.dto.LoginRequest;
import com.hcl.ingMortgage.dto.LoginResponse;
import com.hcl.ingMortgage.entity.User;

/**
 * @author sharath vemperala
 *
 */
public interface LoginService {
	public boolean validateOtp(String username, int verificationCode);
	
	public void updateLoginInfo(User users);

	public int generateOTP(String username);

	public void sendEmail(String email, String message, String subject);
	
	public String generatePassword();
	
	public LoginResponse authenticate(LoginRequest loginRequest);

}