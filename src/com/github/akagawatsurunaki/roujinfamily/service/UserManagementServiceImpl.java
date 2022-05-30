package com.github.akagawatsurunaki.roujinfamily.service;

import java.util.List;

import javax.swing.table.TableModel;

import com.github.akagawatsurunaki.roujinfamily.dao.MemberDao;
import com.github.akagawatsurunaki.roujinfamily.dao.MemberDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDao;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
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
	public boolean addUser(User newUser) throws FileWritingException, CanNotMatchException {
		return userDao.addUser(newUser);
	}
	@Override
	public boolean addMember(Member newMember) throws FileWritingException, CanNotMatchException {
		return memberDao.addMember(newMember);
	}
	
	// #endregion
	
	// #region Clear Methods
	
	@Override
	public boolean clearAllUsers() throws FileWritingException {
		return userDao.clearUserTable();
	}
	@Override
	public boolean clearAllMembers() throws FileWritingException {
		return memberDao.clearMemberTable();
	}
	
	// #endregion
	
	// #region Remove Methods

	@Override
	public boolean removeUser(int id) throws FileWritingException, ObjectNotFoundException {
		return userDao.removeUser(id);
	}
	@Override
	public boolean removeMember(int id) throws FileWritingException, ObjectNotFoundException {
		return memberDao.removeMember(id);
	}
	
	// #endregion
	
	// #region Find Methods
	
	@Override
	public User findUserByName(String userName) throws ObjectNotFoundException {
		return userDao.findUserByUserName(userName);
	}
	@Override
	public Member findMemberByRealName(String realName) throws ObjectNotFoundException{
		return memberDao.findMemberByRealName(realName);
	}
	@Override
	public User findUserById(int id) throws ObjectNotFoundException {
		return userDao.findUserById(id);
	}
	@Override
	public Member findMemberById(int id) throws ObjectNotFoundException {
		return memberDao.findMemberById(id);
	}
	@Override
	public List<Member> findMembersByHouseKeeperId(int houseKeeperId){
		return memberDao.findMembersByHouseKeeperId(houseKeeperId);
	}
	@Override
	public List<User> findUsersByRole(Role role) {
		return userDao.findUsersByRole(role);
	}
	@Override
	public User findUserByRealName(String realName) {
		return userDao.findUserByRealName(realName);
	}
	
	
	// #endregion
	
	// #region Load Methods
	
	@Override
	public boolean loadAllUsers() throws FileReadingException {
		userDao.loadAllUsersFromFile();
		return true;
	}
	@Override
	public boolean loadAllMembers() throws FileReadingException {
		memberDao.loadAllMembersFromFile();
		return true;
	}
	@Override
	public void saveAllMembers() throws FileWritingException {
		memberDao.saveAllMembersToFile();
	}
	
	// #endregion

	// #region Table Getters 
	
	@Override
	public Table<User> getUsersTable() throws FileReadingException {
	 	return userDao.getUserTable();
	}
	@Override
	public Table<Member> getMemberTable() throws FileReadingException{
		return memberDao.getMemberTable();
	}
	
	// #endregion
	
	// #region List Getters
	
	@Override
	public List<Member> getMemberListCanBeAdded(int houseKeeperId) {
		return memberDao.getMemberListCanBeAdded(houseKeeperId);
	}
	
	// #endregion
	@Override
	public TableModel getUserTableModel() throws FileReadingException{
		return userDao.getUserTableModel();
	}
	
	@Override
	public TableModel getMemberEditTableModel(int hskId) {
		return memberDao.getMemberEditTableModel(hskId);
	}
}
