package com.github.akagawatsurunaki.roujinfamily.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.util.Matcher;

public class Member {
	
	// #region Properties
	protected int id;
	protected String realName;
	protected Gender gender = Gender.UNKNOWN;
	protected LocalDate birthday;
	protected String telNumber;
	private int houseKeeperId;
	// #endregion
	
	// #region Getters 
	public int getId() {
		return id;
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
	
	public int getHouseKeeperId() {
		return houseKeeperId;
	}
	// #endregion
	
	// #region Setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setRealName(String realName) throws UserInfoInvalidException {
		if(Matcher.matchReanlNamePattern(realName)) {
			this.realName = realName;
		}
		else {
			throw new UserInfoInvalidException("��ʵ���������ɺ�����ɣ������Ժ�Ӣ����ַ������ҳ���������1��20���ַ���");	
		}
	}
	
	public void setGender(Gender gender) throws UserInfoInvalidException {
		if(Gender.MALE == gender || Gender.FEMALE == gender) {
			this.gender = gender;
		}
		else {
			throw new UserInfoInvalidException("�Ա�ֻ�����л���Ů��");
		}
	}
	
	public void setBirthday(LocalDate birthday) throws UserInfoInvalidException {
		if ((LocalDate.now()).isBefore(birthday)) {
			throw new UserInfoInvalidException("�������ڱ���С�ڵ���ϵͳ��ǰ���ڡ�");
		}
		this.birthday = birthday;
	}
	
	public void setTelNumber(String telNumber) throws UserInfoInvalidException {
		if(Matcher.matchTelNumPattern(telNumber)) {
			this.telNumber = telNumber;
		}
		else {
			throw new UserInfoInvalidException("�ֻ�����������ҹ�ʹ�õ�11λ���롣");
		}
	}
	
	public void setHouseKeeperId(int houseKeeperId) {
		this.houseKeeperId = houseKeeperId;
	}
	// #endregion

	// #region Methods
	public String[] toStringArray() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		String[] retArr = { 
				Integer.toString(id), 
				realName, 
				gender.toString(),
				formatter.format(birthday),
				telNumber,
		};
		return retArr;
	}
	// #endregion
	
}
