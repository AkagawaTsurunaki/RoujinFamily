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

public class UserDaoImpl implements UserDao{

	private static UserDao instance = new UserDaoImpl();
	private static final String filePath = "C:\\Users\\96514\\Desktop\\save\\Test.json";
	private static User loginUser;
	private Table<User> usersTable;
	
	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		
		return instance;
	}
	@Override
	public void initialize() throws UserInfoDataReadingException {
		instance.loadAllUsersFromFile();
	}
	private UserDaoImpl() {}


	@Override
	public boolean login(String userName, String password) throws UserNotFoundException {
		loginUser = findUserByUserName(userName);
		if(loginUser.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public User findUserByUserName(String userName) throws UserNotFoundException {
		List<User> userList = getUsersTable().getData();
		for(User user : userList) {
			if(userName.equals(user.getUserName())) {
				return user;
			}
		}
		throw new UserNotFoundException("�û������ڡ�");
	}

	@Override
	public void loadAllUsersFromFile() throws UserInfoDataReadingException {
		try {
			String str = FileUtil.readFile(filePath);
			this.usersTable = GsonUtil.fromJsonToUserTable(str);
		} catch (IOException e) {
			throw new UserInfoDataReadingException("��ȡ�û���Ϣ�ļ�ʧ�ܡ�");
		}
	}
	@Override
	public void saveAllUsersToFile() throws UserInfoDataWritingException {
		String dataStr = GsonUtil.fromUserTableToJson(usersTable);
		try {
			FileUtil.writeFile(filePath, dataStr);
		} catch (IOException e) {
			throw new UserInfoDataWritingException("д���û���Ϣ�ļ�ʧ�ܡ�");
		}
	}
	
	@Override
	public Table<User> getUsersTable()  {
		return this.usersTable;
	}

}
