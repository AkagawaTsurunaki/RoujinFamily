package com.github.akagawatsurunaki.roujinfamily.service;

import com.github.akagawatsurunaki.roujinfamily.dao.UserDao;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public class UserManagementServiceImpl implements UserManagementService {

	private static UserManagementService instance = new UserManagementServiceImpl();
	
	public static UserManagementService getInstance() {
		return instance;
	}
	
	private UserManagementServiceImpl() {};
	
	private static UserDao userDao = UserDaoImpl.getInstance();
	
	@Override
	public boolean addUser(User newUser) throws UserInfoDataWritingException, UserInfoInvalidException {
		return userDao.addUser(newUser);
	}
	
	@Override
	public boolean clearAllUsers() throws UserInfoDataWritingException {
		return userDao.clearUserTable();
	}

	@Override
	public boolean removeUser(int id) throws UserInfoDataWritingException, UserNotFoundException {
		return userDao.removeUser(id);
	}
	@Override
	public User findUserByName(String userName) throws UserNotFoundException {
		return userDao.findUserByUserName(userName);
	}
	@Override
	public User findUserById(int id) throws UserNotFoundException {
		return userDao.findUserById(id);
	}
	@Override
	public boolean loadAllUsers() throws UserInfoDataReadingException {
		userDao.loadAllUsersFromFile();
		return true;
	}
	@Override
	public Table<User> getUsersTable() throws UserInfoDataReadingException {
	 	return userDao.getUsersTable();
	}
}
