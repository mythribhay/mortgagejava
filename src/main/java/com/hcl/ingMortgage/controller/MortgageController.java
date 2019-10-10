package com.hcl.ingMortgage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ingMortgage.dto.UserResponseDto;
import com.hcl.ingMortgage.service.UserService;

/**
 * 
 * @author Manisha Yadav
 *
 */

@RestController
@RequestMapping("/mortgage")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class MortgageController {

	/*
	 * @Autowired MortgageService mortgageService;
	 * 
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(MortgageController.class);
	 * 
	 * @GetMapping("/{userId}") public ResponseEntity<UserResponseDto>
	 * getUser(@PathVariable Integer userId){
	 * 
	 * logger.info(":: Enter into MortgageController--------::getUser()"); return
	 * new ResponseEntity<>(mortgageService.fetchListO(userId),HttpStatus.OK);
	 * 
	 * }
	 */

}
