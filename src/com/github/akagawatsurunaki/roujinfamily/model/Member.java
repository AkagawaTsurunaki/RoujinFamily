package com.github.akagawatsurunaki.roujinfamily.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.util.GlobalFormatter;
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
	
	public Member(int id, 
			String realName, 
			Gender gender, 
			LocalDate birthday, 
			String telNumber, 
			int houseKeeperId) throws CanNotMatchException {
		setId(id);
		setRealName(realName);
		setGender(gender);
		setBirthday(birthday);
		setTelNumber(telNumber);
		setHouseKeeperId(houseKeeperId);
	}
	
	protected Member() {}
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
	
	public void setRealName(String realName) throws CanNotMatchException {
		if(Matcher.matchReanlNamePattern(realName)) {
			this.realName = realName;
		}
		else {
			throw new CanNotMatchException("��ʵ���������ɺ�����ɣ������Ժ�Ӣ����ַ������ҳ���������1��20���ַ���", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");	
		}
	}
	
	public void setGender(Gender gender) throws CanNotMatchException {
		if(Gender.MALE == gender || Gender.FEMALE == gender) {
			this.gender = gender;
		}
		else {
			throw new CanNotMatchException("�Ա�ֻ�����л���Ů��", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
	}
	
	public void setBirthday(LocalDate birthday) throws CanNotMatchException {
		if ((LocalDate.now()).isBefore(birthday)) {
			throw new CanNotMatchException("�������ڱ���С�ڵ���ϵͳ��ǰ���ڡ�", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
		this.birthday = birthday;
	}
	
	public void setTelNumber(String telNumber) throws CanNotMatchException {
		if(Matcher.matchTelNumPattern(telNumber)) {
			this.telNumber = telNumber;
		}
		else {
			throw new CanNotMatchException("�ֻ�����������ҹ�ʹ�õ�11λ���롣", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
	}
	
	public void setHouseKeeperId(int houseKeeperId) {
		this.houseKeeperId = houseKeeperId;
	}
	// #endregion

	// #region Methods
	public String[] toStringArray() {
		String[] retArr = { 
				Integer.toString(id), 
				realName, 
				gender.toString(),
				GlobalFormatter.dateFormatter.format(birthday),
				telNumber,
		};
		return retArr;
	}
	// #endregion
	
}
