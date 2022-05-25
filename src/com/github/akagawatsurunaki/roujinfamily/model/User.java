package com.github.akagawatsurunaki.roujinfamily.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.swing.text.DateFormatter;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.util.Matcher;
import com.google.gson.JsonPrimitive;
public class User {

	//#region Properties
	private int id;
	private String userName;
	private String password;
	private String realName;
	private Gender gender = Gender.UNKNOWN; 
	private LocalDate birthday;
	private String telNumber;
	private Role role;
	//#endregion
	
	//#region ErrFormatException
	 
	private static void errUserNameFormat() throws UserInfoInvalidException{
		throw new UserInfoInvalidException("�û���ֻ���Ǵ�д��ĸ��Сд��ĸ�����ֵ���ϣ��ҳ���������1��20���ַ���");
	}
	private static void errPasswordFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("������뺬������1����д��ĸ��1��Сд��ĸ��1�����ֺ�@$!%*?&�е�����1�����ҳ���������8��20���ַ���");
	}
	private static void errRealNameFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("��ʵ���������ɺ�����ɣ������Ժ�Ӣ����ַ������ҳ���������1��20���ַ���");
	}
	private static void errGenderFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("�û����Ա�ֻ�����л���Ů��");
	}
	private static void errBirthdayFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("�û����������ڱ���С�ڵ���ϵͳ��ǰ���ڡ�");
	}
	private static void errTelNumFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("�û����ֻ�����������ҹ�ʹ�õ�11λ���롣");
	}
	private static void errRoleFormat() throws UserInfoInvalidException {
		throw new UserInfoInvalidException("�û���ɫ����Ϊ����Ա������ܼҡ����ڹ����е�һ�֡�");
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
		if(Matcher.matchUserNamePattern(userName)) {
			this.userName = userName;
		}
		else { errUserNameFormat(); }
	}
	public void setPassword(String password) throws UserInfoInvalidException {
		if(Matcher.matchPasswordPattern(password)) {
			this.password = password;
		}
		else { errPasswordFormat(); }
	}
	public void setRealName(String realName) throws UserInfoInvalidException {
		if(Matcher.matchReanlNamePattern(realName)) {
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
		if(Matcher.matchTelNumPattern(telNumber)) {
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
	
	public String[] toStringArray() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		String[] retArr = { 
				Integer.toString(id), 
				userName, 
				realName, 
				gender.toString(),
				formatter.format(birthday),
				telNumber,
				role.toString()
		};
		return retArr;
	}

	
}
