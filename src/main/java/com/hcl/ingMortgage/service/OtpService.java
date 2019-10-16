package com.hcl.ingMortgage.service;

import com.hcl.ingMortgage.dto.OptRequestDto;
import com.hcl.ingMortgage.dto.OtpResponseDto;

public interface OtpService {

	public boolean validateOtp(String username, int verificationCode);

	public int generateOTP(String username);
	
	public OtpResponseDto sendOtp(OptRequestDto optRequestDto);

}
