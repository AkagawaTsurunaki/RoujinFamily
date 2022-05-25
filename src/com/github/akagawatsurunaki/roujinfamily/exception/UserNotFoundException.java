package com.github.akagawatsurunaki.roujinfamily.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg) {
		// TODO Auto-generated constructor stub
		System.err.println(msg);
	}

}
