package com.hcl.ingMortgage;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IngMortgageApplication {

	private static final Logger logger = LoggerFactory.getLogger(IngMortgageApplication.class);

	public static void main(String[] args) {
		logger.info(":: Enters main class");

		SpringApplication.run(IngMortgageApplication.class, args);
	}

}
