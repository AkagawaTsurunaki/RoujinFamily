package com.github.akagawatsurunaki.roujinfamily.service;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public interface UserManagementService {

	public boolean addUser(User user)throws UserInfoDataWritingException, UserInfoInvalidException;
	public boolean clearAllUsers() throws UserInfoDataWritingException;
	public boolean removeUser(int id) throws UserInfoDataWritingException, UserNotFoundException;
	
	public User findUserByName(String userName) throws UserNotFoundException;
	public boolean loadAllUsers() throws UserInfoDataReadingException;
	public Table<User> getUsersTable() throws UserInfoDataReadingException;
	User findUserById(int id) throws UserNotFoundException;
	Table<Member> getMemberTable() throws UserInfoDataReadingException;
	boolean loadAllMembers() throws UserInfoDataReadingException;
	Member findMemberByRealName(String realName) throws UserNotFoundException;
	Member findMemberById(int id) throws UserNotFoundException;
	boolean removeMember(int id) throws UserInfoDataWritingException, UserNotFoundException;
	boolean clearAllMembers() throws UserInfoDataWritingException;
	boolean addMember(Member newMember) throws UserInfoDataWritingException, UserInfoInvalidException;

}
