package com.wallet.exceptions;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7542209692111658453L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
	public String getMessage() {
		String msg = super.getMessage();
		return msg;
	}
}
