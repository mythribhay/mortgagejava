package com.hcl.ingMortgage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ingMortgage.IngMortgageApplication;
import com.hcl.ingMortgage.util.BOASendMail;


@Service
public class MailServiceImpl implements MailService {

	private static final Logger logger = LoggerFactory.getLogger(IngMortgageApplication.class);

	@Autowired
	private BOASendMail boaSendEmail;

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
}