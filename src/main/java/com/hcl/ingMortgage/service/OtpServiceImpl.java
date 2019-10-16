package com.hcl.ingMortgage.service;

import java.util.Date;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.ingMortgage.IngMortgageApplication;
import com.hcl.ingMortgage.dto.OptRequestDto;
import com.hcl.ingMortgage.dto.OtpResponseDto;
import com.hcl.ingMortgage.entity.UserOtp;
import com.hcl.ingMortgage.repository.UserOtpRepository;
import com.hcl.ingMortgage.util.ApplicationConstants;
import com.hcl.ingMortgage.util.BOASendMail;
import com.hcl.ingMortgage.util.UserCredentials;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.KeyRepresentation;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder;


@Service
public class OtpServiceImpl implements OtpService {

	private static final Logger logger = LoggerFactory.getLogger(IngMortgageApplication.class);

	@Autowired
	private MailService mailService;

	UserOtpRepository userOtpDao;
	UserOtp userOtp;
	GoogleAuthenticatorConfigBuilder configBuilder;
	GoogleAuthenticatorConfig config;
	GoogleAuthenticator gAuth;

	/**
	 * 
	 * @param credentials
	 * @return boolean
	 */
	@Override
	public boolean validateOtp(String email, int verificationCode) {
		logger.info(":: Enter into LoginController--------::login()");

		userOtp = userOtpDao.getOne(email);
		boolean isCodeValid = false;

		if (userOtp != null) {
			isCodeValid = (userOtp.getValidationcode() == verificationCode)
					&& (new Date().getTime() <= userOtp.getValidity()) ? true : false;
		}
		return isCodeValid;
	}

	/**
	 * 
	 * @param userName
	 * @return otp
	 */

	@Override
	public int generateOTP(String username) {
		logger.info(":: Enter into LoginController--------::login()");

		final GoogleAuthenticatorKey key = gAuth.createCredentials(username);
		return key.getVerificationCode();
	}

	@PostConstruct
	public void initIt() throws Exception {
		configBuilder = new GoogleAuthenticatorConfigBuilder().setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(30))
				.setWindowSize(50).setKeyRepresentation(KeyRepresentation.BASE64);
		config = configBuilder.build();
		gAuth = new GoogleAuthenticator(config);
		UserCredentials userCredentials = new UserCredentials();

	}

	@Override
	public OtpResponseDto sendOtp(OptRequestDto otpRequestDto) {
		logger.info(":: Enter into LoginController--------::login()");
		int otp = generateOTP(otpRequestDto.getEmail());
		mailService.sendEmail(otpRequestDto.getEmail(), Integer.toString(otp), "OTP");
		OtpResponseDto otpResponseDto = new OtpResponseDto();
		otpResponseDto.setMessage(ApplicationConstants.SUCCESS);
		otpResponseDto.setStatusCode(HttpStatus.OK.value());
		return otpResponseDto;
	}

}
