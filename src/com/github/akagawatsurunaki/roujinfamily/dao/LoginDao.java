package com.github.akagawatsurunaki.roujinfamily.dao;
import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public interface LoginDao {

	public boolean login(String userName, String password) throws UserNotFoundException, UserInfoDataReadingException;

	
	public Table<User> getUserTable() throws UserInfoDataReadingException;

	public void writeUserTable(Table<User> userTable) throws UserInfoDataWritingException;
	
	public User findUserByUserName(String userName) throws UserNotFoundException, UserInfoDataReadingException;

}
