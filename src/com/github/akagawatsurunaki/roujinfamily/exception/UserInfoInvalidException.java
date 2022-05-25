package com.github.akagawatsurunaki.roujinfamily.exception;

public class UserInfoInvalidException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private String errMsg;
	
	public String getErrorMessage() {
		return errMsg;
	}
	
	public UserInfoInvalidException(String msg) {
		errMsg = msg;
	}
}
