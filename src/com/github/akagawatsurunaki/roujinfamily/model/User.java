package com.github.akagawatsurunaki.roujinfamily.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.util.Matcher;

public class User extends Member {

	// #region Properties
	private String userName;
	private String password;
	private Role role;
	//#endregion
	
	// #region Constructor
	public User(int id,
			String userName,
			String password,
			String realName,
			Gender gender,
			LocalDate birthday,
			String telNumber,
			Role role) throws CanNotMatchException {
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

	// #region Getters

	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {	
		return password;
	}
	
	public Role getRole() {
		return role;
	}
	//#endregion
	
	// #region Setters

	public void setUserName(String userName) throws CanNotMatchException {
		if(Matcher.matchUserNamePattern(userName)) {
			this.userName = userName;
		}
		else {
			throw new CanNotMatchException("�û���ֻ���Ǵ�д��ĸ��Сд��ĸ�����ֵ���ϣ��ҳ���������1��20���ַ���", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
	}
	public void setPassword(String password) throws CanNotMatchException {
		if(Matcher.matchPasswordPattern(password)) {
			this.password = password;
		}
		else {
			throw new CanNotMatchException("������뺬������1����д��ĸ��1��Сд��ĸ��1�����ֺ�@$!%*?&�е�����1�����ҳ���������8��20���ַ���", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
	}



	public void setRole(Role role) throws CanNotMatchException {
		if (role == Role.ADMINISTRATOR ||
			role == Role.HOUSE_KEEPER ||
			role ==	Role.LOGISTICS) {
			this.role = role;
		}
		else {
			throw new CanNotMatchException("�û���ɫ����Ϊ����Ա������ܼҡ����ڹ����е�һ�֡�", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
	}
	//#endregion
	
	// #region Methods
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
	// #endregion
	
}
