package com.github.akagawatsurunaki.roujinfamily.dao;

import java.io.IOException;
import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.util.FileUtil;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;

public class LoginDaoImpl implements LoginDao{

	private static LoginDao instance = new LoginDaoImpl();
	private static final String filePath = "C:\\Users\\96514\\Desktop\\save\\Test.json";
	
	
	public static LoginDao getInstance() {
		if (instance == null) {
			instance = new LoginDaoImpl();
		}
		return instance;
	}
	
	private LoginDaoImpl() {}

	@Override
	public boolean login(String userName, String password) throws UserNotFoundException, UserInfoDataReadingException {
		// TODO Auto-generated method stub
		User user = findUserByUserName(userName);
		if(user.getPassword().equals(password)) {
			System.out.println("dao层测试返回真值");
			return true;
		}
		System.out.println("dao层测试返回假值");
		return false;
	}
	
	


	@Override
	public Table<User> getUserTable() throws UserInfoDataReadingException {
		try {
			String str = FileUtil.readFile(filePath);
			Table<User> userTable = GsonUtil.fromJsonToUserTable(str);
			return userTable;
		} catch (IOException e) {
			throw new UserInfoDataReadingException("读取用户信息文件失败。");
		}
	}

	@Override
	public void writeUserTable(Table<User> userTable) throws UserInfoDataWritingException {
		try {
			String str = GsonUtil.fromUserTableToJson(userTable);
			FileUtil.writeFile(filePath, str);
		} catch (Exception e) {
			throw new UserInfoDataWritingException("写入用户信息文件失败。");
		}
	}
	@Override
	public User findUserByUserName(String userName) throws UserNotFoundException, UserInfoDataReadingException {
		Table<User> userTable;
		try {
			userTable = getUserTable();
		} catch (UserInfoDataReadingException e) {
			throw new UserInfoDataReadingException("写入用户信息文件失败。");
		}
		List<User> userList = userTable.getDataList();
		for(User user : userList) {
			if(userName.equals(user.getUserName())) {
				return user;
			}
		}
		throw new UserNotFoundException("用户不存在。");
	}

}
