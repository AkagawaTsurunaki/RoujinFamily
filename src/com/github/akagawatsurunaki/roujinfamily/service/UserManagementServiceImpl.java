package com.github.akagawatsurunaki.roujinfamily.service;

import com.github.akagawatsurunaki.roujinfamily.dao.MemberDao;
import com.github.akagawatsurunaki.roujinfamily.dao.MemberDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDao;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public class UserManagementServiceImpl implements UserManagementService {

	private static UserManagementService instance = new UserManagementServiceImpl();
	
	public static UserManagementService getInstance() {
		return instance;
	}
	
	private UserManagementServiceImpl() {};
	
	private static UserDao userDao = UserDaoImpl.getInstance();
	private static MemberDao memberDao = MemberDaoImpl.getInstance();
	
	// #region Add Methods
	
	@Override
	public boolean addUser(User newUser) throws UserInfoDataWritingException, UserInfoInvalidException {
		return userDao.addUser(newUser);
	}
	@Override
	public boolean addMember(Member newMember) throws UserInfoDataWritingException, UserInfoInvalidException {
		return memberDao.addMember(newMember);
	}
	
	// #endregion
	
	// #region Clear Methods
	
	@Override
	public boolean clearAllUsers() throws UserInfoDataWritingException {
		return userDao.clearUserTable();
	}
	@Override
	public boolean clearAllMembers() throws UserInfoDataWritingException {
		return memberDao.clearMemberTable();
	}
	
	// #endregion
	
	// #region Remove Methods

	@Override
	public boolean removeUser(int id) throws UserInfoDataWritingException, UserNotFoundException {
		return userDao.removeUser(id);
	}
	@Override
	public boolean removeMember(int id) throws UserInfoDataWritingException, UserNotFoundException {
		return memberDao.removeMember(id);
	}
	
	// #endregion
	
	// #region Find Methods
	
	@Override
	public User findUserByName(String userName) throws UserNotFoundException {
		return userDao.findUserByUserName(userName);
	}
	@Override
	public Member findMemberByRealName(String realName) throws UserNotFoundException{
		return memberDao.findMemberByRealName(realName);
	}
	@Override
	public User findUserById(int id) throws UserNotFoundException {
		return userDao.findUserById(id);
	}
	@Override
	public Member findMemberById(int id) throws UserNotFoundException {
		return memberDao.findMemberById(id);
	}
	
	// #endregion
	
	// #region Load Methods
	
	@Override
	public boolean loadAllUsers() throws UserInfoDataReadingException {
		userDao.loadAllUsersFromFile();
		return true;
	}
	@Override
	public boolean loadAllMembers() throws UserInfoDataReadingException {
		memberDao.loadAllMembersFromFile();
		return true;
	}
	
	// #endregion

	// #region Table Getters 
	
	@Override
	public Table<User> getUsersTable() throws UserInfoDataReadingException {
	 	return userDao.getUserTable();
	}
	@Override
	public Table<Member> getMemberTable() throws UserInfoDataReadingException{
		return memberDao.getMemberTable();
	}
	
	// #endregion
}
