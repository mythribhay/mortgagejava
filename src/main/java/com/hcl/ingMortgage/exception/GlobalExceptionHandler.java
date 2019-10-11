package com.hcl.ingMortgage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(InvalidUserException exception) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MortgageNotFoundException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(MortgageNotFoundException exception) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}
	
}
