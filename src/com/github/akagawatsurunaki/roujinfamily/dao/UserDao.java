package com.github.akagawatsurunaki.roujinfamily.dao;

import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public interface UserDao {
	
	public void initialize() throws FileReadingException;
	// Login method
	public Role login(String userName, String password) throws ObjectNotFoundException;
	// Get User Table from local static variable.
	public Table<User> getUserTable() throws FileReadingException;
	// 
	public void saveAllUsersToFile() throws FileWritingException;
		
	public User findUserByUserName(String userName) throws ObjectNotFoundException;
	// Read users table from file and assign to local variable.
	public void loadAllUsersFromFile() throws FileReadingException;

	// Add new User into static table and save it automatcially
	// If update and save successfully, it will return TRUE
	public boolean addUser(User user) throws FileWritingException, CanNotMatchException;
	// Clear the whole user table and save it to the file.
	public boolean clearUserTable() throws FileWritingException;
	//
	public boolean removeUser(int id) throws FileWritingException, ObjectNotFoundException;
	//
	public User findUserById(int id) throws ObjectNotFoundException;
	public List<User> findUsersByRole(Role role);
	User findUserByRealName(String realName);
	int findUserIdByPassword(String password);
}
