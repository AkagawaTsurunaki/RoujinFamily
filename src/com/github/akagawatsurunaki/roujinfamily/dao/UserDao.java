package com.github.akagawatsurunaki.roujinfamily.dao;
import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public interface UserDao {
	public void initialize() throws UserInfoDataReadingException;
	// Login method
	public boolean login(String userName, String password) throws UserNotFoundException;
	// Get User Table from local static variable.
	public Table<User> getUsersTable() throws UserInfoDataReadingException;
	// 
	public void saveAllUsersToFile() throws UserInfoDataWritingException;
		
	public User findUserByUserName(String userName) throws UserNotFoundException;
	// Read users table from file and assign to local variable.
	public void loadAllUsersFromFile() throws UserInfoDataReadingException;

	// Add new User into static table and save it automatcially
	// If update and save successfully, it will return TRUE
	public boolean addUser(User user) throws UserInfoDataWritingException, UserInfoInvalidException;
	// Clear the whole user table and save it to the file.
	public boolean clearUserTable() throws UserInfoDataWritingException;
	//
	public boolean removeUser(int id) throws UserInfoDataWritingException, UserNotFoundException;
	//
	public User findUserById(int id) throws UserNotFoundException;
}
