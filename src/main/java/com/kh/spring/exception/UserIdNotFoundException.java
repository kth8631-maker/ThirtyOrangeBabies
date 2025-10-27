package com.kh.spring.exception;

public class UserIdNotFoundException extends RuntimeException {
	
	public UserIdNotFoundException(String msg) {
		super(msg);
	}

}
