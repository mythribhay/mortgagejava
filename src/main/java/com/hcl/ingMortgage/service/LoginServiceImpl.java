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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ingMortgage.IngMortgageApplication;
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
	private static final Logger logger = LoggerFactory.getLogger(IngMortgageApplication.class);

	@Autowired
	private BOASendMail boaSendEmail;

	@Autowired
	private UserRepository usersDao;

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

	/**
	 * 
	 * @param email attributes
	 * @return void
	 */

	@Override
	public void sendEmail(String emailId, String message, String subject) {
		logger.info(":: Enter into LoginController--------::login()");

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
		logger.info(":: Enter into LoginController--------::login()");

		usersDao.save(users);
	}

	@Override
	public String generatePassword() {
		logger.info(":: Enter into LoginController--------::login()");

		return RandomStringUtils.randomAlphanumeric(10);

	}

	/**
	 * 
	 * @param login credentials
	 * @return LoginResponse
	 */

	@Override
	public LoginResponse authenticate(LoginRequest loginRequest) {
		logger.info(":: Enter into LoginController--------::login()");

		String message = "";
		LoginResponse loginResponse = new LoginResponse();
		Optional<User> user = usersDao.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

		if (!user.isPresent()) {
			Optional<User> failedUser = usersDao.findByEmail(loginRequest.getEmail());

			message = "Invalid username/password";

			if (failedUser.isPresent()) {
				System.out.println("Failure Attempts in controller : " + failedUser.get().getFailure());

				if (failedUser.get().getFailure() >= 3) {
					if (failedUser.get().getFailure() == 3) {
						User updateuser = usersDao.findByEmail(failedUser.get().getEmail()).get();
						String password = generatePassword();

						System.out.println("password:" + password);

						updateuser.setPassword(password);
						updateuser.setFailure(0);
						usersDao.save(updateuser);
					}
					message = "failed attempts exceeded. please contact admin.";
					loginResponse.setMessage(message);
					loginResponse.setStatusCode(401);
					loginResponse.setUserId(0);

				}

				else {
					User failedUserO = failedUser.get();
					failedUserO.setFailure(failedUserO.getFailure() + 1);
					usersDao.save(failedUserO);
					message = "password missmatch";
					loginResponse.setMessage(message);
					loginResponse.setStatusCode(401);
					loginResponse.setUserId(0);

				}

			} else {

				message = "username doesnt exist";
				loginResponse.setMessage(message);
				loginResponse.setStatusCode(401);
				loginResponse.setUserId(0);

			}

		} else {
			message = "success";
			loginResponse.setMessage(message);
			loginResponse.setStatusCode(201);

			if (user.isPresent()) {
				loginResponse.setUserId(user.get().getUserId());
			} else {
				loginResponse.setUserId(0);
			}
		}
		return loginResponse;
	}

}
