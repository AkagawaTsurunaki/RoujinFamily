package com.github.akagawatsurunaki.roujinfamily.util;

import java.util.regex.Pattern;

import com.github.akagawatsurunaki.roujinfamily.model.Constants;

/**
 * @author Akagawa Tsurunaki
 *
 */
public class Matcher {

	//#region Matcher
	
	// Chinese characters only
	public static boolean matchReanlNamePattern(String str) {
		String pattern = "[\\u4E00-\\u9FA5]+{" 
				+ Constants.MIN_REALNAME_LENGTH
				+ ","
				+ Constants.MAX_REALNAME_LENGTH
				+ "}";
		return Pattern.matches(pattern, str);
	}
	// Only a-z A-Z numbers and $@!%*?&,
	// at least 1 capital letter and 
	// 1 lower case letter and 
	// 1 number and other allowed characters
	public static boolean matchPasswordPattern(String str) {

		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)"
				+ "(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{" 
				+ Constants.MIN_PASSWORD_LENGTH 
				+ "," 
				+ Constants.MAX_PASSWORD_LENGTH + "}";
		return Pattern.matches(pattern, str);
	}
	// Only a-z A-Z 0-9
	public static boolean matchUserNamePattern(String str) { 
		String pattern = "[a-zA-Z0-9]{" 
				+ Constants.MIN_USERNAME_LENGYH
				+ ","
				+ Constants.MAX_USERNAME_LENGYH
				+ "}";
		return Pattern.matches(pattern, str);
	}
	// Chinese telephone number only
	public static boolean matchTelNumPattern(String str) {
		String pattern = "^1[3-9]\\d{9}$";
		return Pattern.matches(pattern, str);
	}
	//#endregion

}
