package com.app.myconame.exception;

public class NoSuchPlanExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoSuchPlanExistsException() {
		super();
	}

	public NoSuchPlanExistsException(String msg) {
		super(msg);
	}
}
