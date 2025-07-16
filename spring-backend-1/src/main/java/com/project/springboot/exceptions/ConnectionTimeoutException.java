package com.project.springboot.exceptions;

public class ConnectionTimeoutException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ConnectionTimeoutException(String message) {
		super(message);
	}

}	
