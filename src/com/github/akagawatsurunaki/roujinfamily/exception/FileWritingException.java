package com.github.akagawatsurunaki.roujinfamily.exception;
/**
 * @author Akagawa Tsurunaki
 *
 */
public class FileWritingException extends RouJinFamilyException {

	private static final long serialVersionUID = 1L;

	public FileWritingException(String errorMessage, String title, String positionInfo) {
		this.errorMessage = errorMessage;
		this.title = title;
		this.positionInfo = positionInfo;
	}

}
