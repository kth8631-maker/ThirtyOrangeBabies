package com.kh.spring.exception;

public class AuthenticationException extends RuntimeException {
	
	public AuthenticationException(String msg) {
		super(msg);
	}

}
