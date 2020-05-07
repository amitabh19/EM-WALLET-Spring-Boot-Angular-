package com.wallet.exceptions;

public class ListNotFoundException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3288655890952338411L;

	public ListNotFoundException(String message) {
		super(message);
	}

	public String getMessage() {
		String msg = super.getMessage();
		return msg;
	}
}
