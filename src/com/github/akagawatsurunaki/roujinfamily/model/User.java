package com.github.akagawatsurunaki.roujinfamily.model;

import java.time.LocalDate;
import java.util.regex.Pattern;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
public class User {

	//#region Properties
	private int id;
	private String userName;
	private String password;
	private String realName;
	private Gender gender = DEFAULT_GENDER; 
	private LocalDate birthday;
	private String telNumber;
	private Role role;
	private final static int MIN_USERNAME_LENGYH = 1;
	private final static int MAX_USERNAME_LENGYH = 20;
	private final static int MIN_PASSWORD_LENGTH = 8;
	private final static int MAX_PASSWORD_LENGTH = 20;
	private final static int MIN_REALNAME_LENGTH = 1;
	private final static int MAX_REALNAME_LENGTH = 20;
	private final static Gender DEFAULT_GENDER = Gender.UNKNOWN;
	//#endregion
	
	//#region ErrFormatException
	 
	private void errUserNameFormat() throws UserInfoInvalidException{
		throw new UserInfoInvalidException("用户名只能是大写字母、小写字母和数字的组合，且长度限制在1到20个字符。");
	}
	private void errPasswordFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("密码必须含有至少1个大写字母、1个小写字母、1个数字和@$!%*?&中的任意1个，且长度限制在8到20个字符。");
	}
	private void errRealNameFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("真实姓名必须由汉字组成（不可以含英语等字符），且长度限制在1到20个字符。");
	}
	private void errGenderFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("用户的性别只能是男或者女。");
	}
	private void errBirthdayFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("用户的生日日期必须小于等于系统当前日期。");
	}
	private void errTelNumFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("用户的手机号码必须是我国使用的11位号码。");
	}
	private void errRoleFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("用户角色必须为管理员、生活管家、后勤管理中的一种。");
	}
	
	//#endregion
	
	//#region Constructor
	public User(int id,
			String userName,
			String password,
			String realName,
			Gender gender,
			LocalDate birthday,
			String telNumber,
			Role role) throws UserInfoInvalidException {
		setId(id);
		setUserName(userName);
		setPassword(password);
		setRealName(realName);
		setGender(gender);
		setBirthday(birthday);
		setTelNumber(telNumber);
		setRole(role);
	}

	//#endregion

	//#region Getters
	public int getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {	
		return password;
	}
	public String getRealName() {
		return realName;
	}
	public Gender getGender() {
		return gender;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public Role getRole() {
		return role;
	}
	//#endregion
	
	//#region Setters
	public void setId(int id) {
		this.id = id;
	}
	public void setUserName(String userName) throws UserInfoInvalidException {
		if(matchUserNamePattern(userName)) {
			this.userName = userName;
		}
		else { errUserNameFormat(); }
	}
	public void setPassword(String password) throws UserInfoInvalidException {
		if(matchPasswordPattern(password)) {
			this.password = password;
		}
		else { errPasswordFormat(); }
	}
	public void setRealName(String realName) throws UserInfoInvalidException {
		if(matchReanlNamePattern(realName)) {
			this.realName = realName;
		}
		else { errRealNameFormat(); }
	}
	public void setGender(Gender gender) throws UserInfoInvalidException {
		if(Gender.MALE == gender || Gender.FEMALE == gender) {
			this.gender = gender;
		}
		else { errGenderFormat(); }
	}
	public void setBirthday(LocalDate birthday) throws UserInfoInvalidException {
		if ((LocalDate.now()).isBefore(birthday)) {
			errBirthdayFormat();
		}
		this.birthday = birthday;
	}
	public void setTelNumber(String telNumber) throws UserInfoInvalidException {
		if(matchTelNumPattern(telNumber)) {
			this.telNumber = telNumber;
		}
		else { errTelNumFormat(); }
	}
	public void setRole(Role role) throws UserInfoInvalidException {
		if (role == Role.ADMINISTRATOR ||
			role == Role.HOUSE_KEEPER ||
			role ==	Role.LOGISTICS) {
			this.role = role;
		}
		else { errRoleFormat(); }
	}
	//#endregion
	
	//#region Matcher
	
	// Chinese characters only
	private boolean matchReanlNamePattern(String str) {
		String pattern = "[\\u4E00-\\u9FA5]+{" 
				+ MIN_REALNAME_LENGTH
				+ ","
				+ MAX_REALNAME_LENGTH
				+ "}";
		return Pattern.matches(pattern, str);
	}
	// Only a-z A-Z numbers and $@!%*?&,
	// at least 1 capital letter and 
	// 1 lower case letter and 
	// 1 number and other allowed characters
	private boolean matchPasswordPattern(String str) {

		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)"
				+ "(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{" 
				+ MIN_PASSWORD_LENGTH 
				+ "," 
				+ MAX_PASSWORD_LENGTH + "}";
		return Pattern.matches(pattern, str);
	}
	// Only a-z A-Z 0-9
	private boolean matchUserNamePattern(String str) { 
		String pattern = "[a-zA-Z0-9]{" 
				+ MIN_USERNAME_LENGYH
				+ ","
				+ MAX_USERNAME_LENGYH
				+ "}";
		return Pattern.matches(pattern, str);
	}
	// Chinese telephone number only
	private boolean matchTelNumPattern(String str) {
		String pattern = "^1[3-9]\\d{9}$";
		return Pattern.matches(pattern, str);
	}
	//#endregion
	
}
