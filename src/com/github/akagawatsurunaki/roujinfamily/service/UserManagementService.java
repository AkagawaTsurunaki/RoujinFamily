package com.github.akagawatsurunaki.roujinfamily.service;

import com.github.akagawatsurunaki.roujinfamily.dao.*;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public interface UserManagementService {

	public boolean addUser(User user)throws UserInfoDataWritingException;
	public boolean clearAllUsers() throws UserInfoDataWritingException;
	public boolean removeUser(User user) throws UserInfoDataWritingException;
	public boolean editUser(User newUser, User oriUser) throws UserInfoDataWritingException, UserInfoInvalidException;
	public User findUserByName(String userName) throws UserNotFoundException;

}
