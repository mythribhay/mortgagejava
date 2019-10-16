package com.hcl.ingMortgage.service;

/*


*
 * 
 */

import java.util.Optional;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.hcl.ingMortgage.IngMortgageApplication;
import com.hcl.ingMortgage.dto.LoginRequest;
import com.hcl.ingMortgage.dto.LoginResponse;
import com.hcl.ingMortgage.dto.ResetPasswordRequest;
import com.hcl.ingMortgage.dto.ResetPasswordResponse;
import com.hcl.ingMortgage.entity.User;
import com.hcl.ingMortgage.exception.InvalidCredentialsException;
import com.hcl.ingMortgage.exception.InvalidUserException;
import com.hcl.ingMortgage.exception.OtpVerificationFailed;
import com.hcl.ingMortgage.repository.UserRepository;
import com.hcl.ingMortgage.util.ApplicationConstants;

/**
 * @authors sharath vemperala
 *
 */

@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(IngMortgageApplication.class);

	@Autowired
	private UserRepository usersDao;

	@Autowired
	private OtpService otpService;

	@Autowired
	private MailService mailService;

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
	 * @throws InvalidCredentialsException
	 */

	@Override
	public LoginResponse authenticate(LoginRequest loginRequest) throws InvalidCredentialsException {
		logger.info(":: Enter into LoginService--------::authenticate()");

		String message = "";
		LoginResponse loginResponse = new LoginResponse();
		Optional<User> user = usersDao.findByEmailAndPasswordAndLocker(loginRequest.getEmail(),
				loginRequest.getPassword(), false);

		if (!user.isPresent()) {
			Optional<User> failedUser = usersDao.findByEmail(loginRequest.getEmail());

			message = ApplicationConstants.INVALID_CREDENTIALS;

			if (failedUser.isPresent()) {
				logger.info("Failure Attempts in controller : " + failedUser.get().getFailure());

				if (failedUser.get().getFailure() >= 3) {
					if (failedUser.get().getFailure() == 3) {
						User updateuser = usersDao.findByEmail(failedUser.get().getEmail()).get();
						// String password = generatePassword();

						// System.out.println("password:" + password);

						// updateuser.setPassword(password);
						updateuser.setFailure(0);
						updateuser.setLocker(true);
						usersDao.save(updateuser);
						// sendEmail(failedUser.get().getEmail(), password, "password");
					}
					message = ApplicationConstants.FAILED_ATTEMPTS_EXCEEDED;
					throw new InvalidCredentialsException(message);

				}

				else {
					User failedUserO = failedUser.get();
					failedUserO.setFailure(failedUserO.getFailure() + 1);
					usersDao.save(failedUserO);
					message = ApplicationConstants.PASSWORD_MISSMATCH;
					throw new InvalidCredentialsException(message);

				}

			} else {

				message = ApplicationConstants.USER_DOESNT_EXIST;
				throw new InvalidCredentialsException(message);

			}

		} else {
			message = ApplicationConstants.SUCCESS;
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

	@Override
	public ResetPasswordResponse forgetPassword(ResetPasswordRequest resetPasswordRequest) throws OtpVerificationFailed {

		Optional<User> userO = usersDao.findByEmail(resetPasswordRequest.getEmail());
		boolean isCodeValid = otpService.validateOtp(resetPasswordRequest.getEmail(), resetPasswordRequest.getOtp());
		User user = null;
		ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
		if (userO.isPresent() && isCodeValid) {
			user = userO.get();
			user.setPassword(resetPasswordRequest.getNewPassword());
			user.setLocker(false);
			usersDao.save(user);
			resetPasswordResponse.setMessage(ApplicationConstants.SUCCESS);
			resetPasswordResponse.setStatusCode(HttpStatus.OK.value());
			resetPasswordResponse.setUserId(user.getUserId());
		} else {
			if(userO.isPresent()) {
				throw new InvalidUserException(ApplicationConstants.INVALID_CREDENTIALS);
			}else if(!isCodeValid) {
				throw new OtpVerificationFailed(ApplicationConstants.OTP_VERIFICATION_FAILED);
			}
		}
		return resetPasswordResponse;

	}

}
