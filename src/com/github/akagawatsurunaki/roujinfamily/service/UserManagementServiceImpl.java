package com.github.akagawatsurunaki.roujinfamily.service;

import com.github.akagawatsurunaki.roujinfamily.dao.UserDao;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public class UserManagementServiceImpl implements UserManagementService {

	private static UserManagementService instance = new UserManagementServiceImpl();
	
	public static UserManagementService getInstance() {
		return instance;
	}
	
	private UserManagementServiceImpl() {};
	
	private static UserDao userDao = UserDaoImpl.getInstance();
	
	@Override
	public boolean addUser(User user) throws UserInfoDataWritingException {
		return userDao.addUser(user);
	}
	
	@Override
	public boolean clearAllUsers() throws UserInfoDataWritingException {
		return userDao.clearUserTable();
	}

	@Override
	public boolean removeUser(User user) throws UserInfoDataWritingException {
		return userDao.removeUser(user);
	}
	@Override
	public boolean editUser(User newUser, User oriUser) throws UserInfoDataWritingException, UserInfoInvalidException {
		return userDao.editUser(newUser, oriUser);
	}
	public User findUserByName(String userName) throws UserNotFoundException {
		return userDao.findUserByUserName(userName);
	}
}
