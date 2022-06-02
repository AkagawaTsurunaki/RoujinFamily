package com.github.akagawatsurunaki.roujinfamily.exception;

/**
 * @author Akagawa Tsurunaki
 *
 */
public class CanNotMatchException extends RouJinFamilyException {
	
	private static final long serialVersionUID = 1L;

	public CanNotMatchException(String errorMessage, String title, String positionInfo) {
		this.errorMessage = errorMessage;
		this.title = title;
		this.positionInfo = positionInfo;
	}
}
