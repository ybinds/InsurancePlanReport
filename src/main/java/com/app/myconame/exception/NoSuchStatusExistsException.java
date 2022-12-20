package com.app.myconame.exception;

public class NoSuchStatusExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchStatusExistsException() {
		super();
	}
	
	public NoSuchStatusExistsException(String msg) {
		super(msg);
	}
}
