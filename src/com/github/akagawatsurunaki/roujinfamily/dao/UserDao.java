package com.github.akagawatsurunaki.roujinfamily.dao;
import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public interface UserDao {
	public void initialize() throws UserInfoDataReadingException;
	// Login method
	public boolean login(String userName, String password) throws UserNotFoundException;
	// Get User Table from local static variable.
	public Table<User> getUsersTable() throws UserInfoDataReadingException;

	public void saveAllUsersToFile() throws UserInfoDataWritingException;
		
	public User findUserByUserName(String userName) throws UserNotFoundException;
	// Read users table from file and assign to local variable.
	public void loadAllUsersFromFile() throws UserInfoDataReadingException;
}
