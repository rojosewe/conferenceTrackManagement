package com.thoughtworks.trackmanagement.exception;

public class InvalidTalkSpecsException extends Exception {

	public InvalidTalkSpecsException(Exception e) {

	}

	public InvalidTalkSpecsException(Throwable cause) {
		super(cause);
	}

	public InvalidTalkSpecsException(String message) {
		super(message);
	}

}
