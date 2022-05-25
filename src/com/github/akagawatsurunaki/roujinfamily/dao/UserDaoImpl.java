package com.github.akagawatsurunaki.roujinfamily.dao;

import java.io.IOException;
import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.util.FileUtil;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;

public class UserDaoImpl implements UserDao {

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

	private UserDaoImpl() {
	}

	@Override
	public boolean login(String userName, String password) throws UserNotFoundException {
		loginUser = findUserByUserName(userName);
		if (loginUser.getPassword().equals(password)) {
			return true;
		}
		return false;
	}


	@Override
	public void loadAllUsersFromFile() throws UserInfoDataReadingException {
		try {
			String str = FileUtil.readFile(filePath);
			this.usersTable = GsonUtil.fromJsonToUserTable(str);
		} catch (IOException e) {
			throw new UserInfoDataReadingException("读取用户信息文件失败。");
		}
	}

	@Override
	public void saveAllUsersToFile() throws UserInfoDataWritingException {
		String dataStr = GsonUtil.fromUserTableToJson(usersTable);
		try {
			FileUtil.writeFile(filePath, dataStr);
		} catch (IOException e) {
			throw new UserInfoDataWritingException("写入用户信息文件失败。");
		}
	}

	@Override
	public Table<User> getUsersTable() {
		return this.usersTable;
	}

	@Override
	public boolean addUser(User newUser) throws UserInfoDataWritingException, UserInfoInvalidException {
		
		List<User> userList = getUsersTable().getData();
		for (User user : userList) {
			if(user.getId() == newUser.getId()) {
				user.setUserName(newUser.getUserName());
				user.setPassword(newUser.getPassword());
				user.setRealName(newUser.getRealName());
				user.setRole(newUser.getRole());
				user.setTelNumber(newUser.getTelNumber());;
				user.setGender(newUser.getGender());
				user.setBirthday(newUser.getBirthday());
				saveAllUsersToFile();
				return true;
			}
		}
		
		newUser.setId(usersTable.getIdCount() + 1);
		usersTable.addDataSeg(newUser);
		saveAllUsersToFile();
			
		
		return false;
	}

	@Override
	public boolean clearUserTable() throws UserInfoDataWritingException {
		usersTable.clear();
		saveAllUsersToFile();
		return true;
	}

	@Override
	public boolean removeUser(int id) throws UserInfoDataWritingException, UserNotFoundException {
		usersTable.removeDataSeg(findUserById(id)); 
		saveAllUsersToFile();
		return true; 
		
	}
	@Override
	public User findUserByUserName(String userName) throws UserNotFoundException {
		List<User> userList = getUsersTable().getData();
		for (User user : userList) {
			if (userName.equals(user.getUserName())) {
				return user;
			}
		}
		throw new UserNotFoundException("用户不存在。");
	}
	@Override
	public User findUserById(int id) throws UserNotFoundException {
		List<User> userList = getUsersTable().getData();
		for (User user : userList) {
			if (user.getId() == id) {
				return user;
			}
		}
		throw new UserNotFoundException("用户不存在。");
	}


	

}
