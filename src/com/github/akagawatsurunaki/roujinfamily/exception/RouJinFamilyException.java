package com.github.akagawatsurunaki.roujinfamily.exception;

public class RouJinFamilyException extends Exception {
	

	private static final long serialVersionUID = 1L;
	protected String errorMessage;
	protected String title;
	protected String positionInfo;

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getTitle() {
		return title;
	}

	public String getPositionInfo() {
		return positionInfo;
	}
	
	protected RouJinFamilyException() {
		
	}

	public RouJinFamilyException(String msg, String title, String pos) {
		this.errorMessage = msg;
		this.title = title;
		this.positionInfo = pos;
	}
}
