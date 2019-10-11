package com.hcl.ingMortgage.util;

import java.util.Date;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.ingMortgage.entity.UserOtp;
import com.hcl.ingMortgage.repository.UserOtpRepository;
import com.warrenstrange.googleauth.ICredentialRepository;

/**
 * @author sharath vemperala
 *
 */



public class UserCredentials implements ICredentialRepository {

	static final int tolerance = 5 * 60 * 1000;

	@Autowired
	UserOtpRepository userOtpRepository;
	
	@Override
	public String getSecretKey(String email) {
		// TODO Auto-generated method stub
		UserOtp user = new UserOtp();
		user = userOtpRepository.getOne(email);
		return user.getSecretKey();	}

	@Override
	public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
		// TODO Auto-generated method stub
		
		UserOtp userOtp = new UserOtp();
		userOtp.setEmail(userName);
		userOtp.setSecretKey(secretKey);
		userOtp.setValidationcode(validationCode);
		userOtp.setValidity(new Date().getTime() + tolerance);
		
	}

}
