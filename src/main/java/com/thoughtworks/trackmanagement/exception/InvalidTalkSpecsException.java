package com.thoughtworks.trackmanagement.exception;

public class InvalidTalkSpecsException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidTalkSpecsException(Exception e) {

	}

	public InvalidTalkSpecsException(Throwable cause) {
		super(cause);
	}

	public InvalidTalkSpecsException(String message) {
		super(message);
	}

}
