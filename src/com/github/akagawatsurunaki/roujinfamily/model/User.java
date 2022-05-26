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
			throw new CanNotMatchException("用户名只能是大写字母、小写字母和数字的组合，且长度限制在1到20个字符。", "非法输入", "该错误是由模型层发起的。");
		}
	}
	public void setPassword(String password) throws CanNotMatchException {
		if(Matcher.matchPasswordPattern(password)) {
			this.password = password;
		}
		else {
			throw new CanNotMatchException("密码必须含有至少1个大写字母、1个小写字母、1个数字和@$!%*?&中的任意1个，且长度限制在8到20个字符。", "非法输入", "该错误是由模型层发起的。");
		}
	}



	public void setRole(Role role) throws CanNotMatchException {
		if (role == Role.ADMINISTRATOR ||
			role == Role.HOUSE_KEEPER ||
			role ==	Role.LOGISTICS) {
			this.role = role;
		}
		else {
			throw new CanNotMatchException("用户角色必须为管理员、生活管家、后勤管理中的一种。", "非法输入", "该错误是由模型层发起的。");
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
