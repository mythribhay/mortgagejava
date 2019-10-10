package com.hcl.ingMortgage.service;

/*
*
 * 
 */

import java.util.Date;


import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.ingMortgage.dto.LoginRequest;
import com.hcl.ingMortgage.dto.LoginResponse;
import com.hcl.ingMortgage.entity.User;
import com.hcl.ingMortgage.entity.UserOtp;
import com.hcl.ingMortgage.repository.UserOtpRepository;
import com.hcl.ingMortgage.repository.UserRepository;
import com.hcl.ingMortgage.util.BOASendMail;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.KeyRepresentation;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder;

/**
 * @authors sharath vemperala
 *
 */

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private BOASendMail boaSendEmail;

	@Autowired
	private UserRepository usersDao;

	

	UserOtpRepository userOtpDao;
	UserOtp userOtp;
	GoogleAuthenticatorConfigBuilder configBuilder;
	GoogleAuthenticatorConfig config;
	GoogleAuthenticator gAuth;

	@Override
	public boolean validateOtp(String email, int verificationCode) {
		userOtp = userOtpDao.getOne(email);
		boolean isCodeValid = false;

		if (userOtp != null) {
			isCodeValid = (userOtp.getValidationcode() == verificationCode)
					&& (new Date().getTime() <= userOtp.getValidity()) ? true : false;
		}
		return isCodeValid;
	}

	@Override
	public int generateOTP(String username) {
		final GoogleAuthenticatorKey key = gAuth.createCredentials(username);
		return key.getVerificationCode();
	}

	@Override
	public void sendEmail(String emailId, String message, String subject) {
		boaSendEmail.SendMailToCustomer(emailId, subject, message);
	}

	@PostConstruct
	public void initIt() throws Exception {
		configBuilder = new GoogleAuthenticatorConfigBuilder().setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(30))
				.setWindowSize(50).setKeyRepresentation(KeyRepresentation.BASE64);
		config = configBuilder.build();
		gAuth = new GoogleAuthenticator(config);

	}

	@Override
	public void updateLoginInfo(User users) {
		usersDao.save(users);
	}

	@Override
	public String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);

	}

	@Override
	public LoginResponse authenticate(LoginRequest loginRequest) {

		String message = "";
//		String password = generatePassword();
//		StandardPasswordEncoder encryption1 = new StandardPasswordEncoder();
//		String pass = encryption1.encode(loginRequest.getPassword());
		Optional<User> user = usersDao.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

		if (!user.isPresent()) {
			Optional<User> failedUser = usersDao.findByEmail(loginRequest.getEmail());
			
			message = "Invalid username/password";

			
			if (failedUser.isPresent()) {
				System.out.println("Failure Attempts in controller : " +failedUser.get().getFailure());

				if (failedUser.get().getFailure() >= 3) {
					//System.out.println("Failure Attempts in controller : " + user.get().getFailure());
					if (failedUser.get().getFailure() == 3) {
						User updateuser = usersDao.findByEmail(failedUser.get().getEmail()).get();
						String password = generatePassword();
						
						System.out.println("password:"+password);

						//StandardPasswordEncoder encryption = new StandardPasswordEncoder();
						updateuser.setPassword(password);
						updateuser.setFailure(0);
						usersDao.save(updateuser);
						//sendEmail(failedUser.get().getUsername(), password, "password");
					}
						message = "Your password was reset. A temporary password was mailed to your email-id";
				}
				
				else {
					User failedUserO = failedUser.get();
					failedUserO.setFailure(failedUserO.getFailure()+1);
					usersDao.save(failedUserO);
					message = "password missmatch";

				}
				
			}else {
				
				message = "username doesnt exist";
			}
			
			


		} else
			message = "success";
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setMessage(message);
		return loginResponse;
	}

}
