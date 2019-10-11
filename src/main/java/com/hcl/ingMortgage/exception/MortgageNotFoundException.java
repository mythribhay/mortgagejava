package com.hcl.ingMortgage.exception;

public class MortgageNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MortgageNotFoundException(String message)
	
	{
		super(message);
	}
}
