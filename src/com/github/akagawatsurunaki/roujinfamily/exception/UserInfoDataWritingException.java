package com.github.akagawatsurunaki.roujinfamily.exception;

public class UserInfoDataWritingException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserInfoDataWritingException(String msg) {
		System.err.println(msg);
	}

}
