package com.kh.spring.exception;

public class TooLargeValueException extends RuntimeException {

	public TooLargeValueException(String message) {
		super(message);
	}

}
