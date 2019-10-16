package com.hcl.ingMortgage.service;

import com.hcl.ingMortgage.dto.LoginRequest;
import com.hcl.ingMortgage.dto.LoginResponse;
import com.hcl.ingMortgage.dto.ResetPasswordRequest;
import com.hcl.ingMortgage.dto.ResetPasswordResponse;
import com.hcl.ingMortgage.entity.User;
import com.hcl.ingMortgage.exception.InvalidCredentialsException;
import com.hcl.ingMortgage.exception.OtpVerificationFailed;

/**
 * @author sharath vemperala
 *
 */
public interface LoginService {
	
	public void updateLoginInfo(User users);
	
	public String generatePassword();
	
	public LoginResponse authenticate(LoginRequest loginRequest) throws InvalidCredentialsException;

	public ResetPasswordResponse forgetPassword(ResetPasswordRequest resetPasswordRequest) throws OtpVerificationFailed;

}