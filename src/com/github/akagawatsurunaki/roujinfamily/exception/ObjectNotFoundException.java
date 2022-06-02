package com.github.akagawatsurunaki.roujinfamily.exception;

/**
 * @author Akagawa Tsurunaki
 *
 */
public class ObjectNotFoundException extends RouJinFamilyException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String errorMessage, String title, String positionInfo) {
		this.errorMessage = errorMessage;
		this.title = title;
		this.positionInfo = positionInfo;
	}

}
