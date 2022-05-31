package com.github.akagawatsurunaki.roujinfamily.service;

import java.util.List;

import javax.swing.table.TableModel;

import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public interface UserManagementService {

	public boolean addUser(User user)throws FileWritingException, CanNotMatchException;
	public boolean clearAllUsers() throws FileWritingException;
	public boolean removeUser(int id) throws FileWritingException, ObjectNotFoundException;
	public User findUserByName(String userName) throws ObjectNotFoundException;
	public boolean loadAllUsers() throws FileReadingException;
	public Table<User> getUsersTable() throws FileReadingException;
	User findUserById(int id) throws ObjectNotFoundException;
	Table<Member> getMemberTable() throws FileReadingException;
	boolean loadAllMembers() throws FileReadingException;
	Member findMemberByRealName(String realName) throws ObjectNotFoundException;
	Member findMemberById(int id) throws ObjectNotFoundException;
	boolean removeMember(int id) throws FileWritingException, ObjectNotFoundException;
	boolean clearAllMembers() throws FileWritingException;
	boolean addMember(Member newMember) throws FileWritingException, CanNotMatchException;
	List<Member> findMembersByHouseKeeperId(int houseKeeperId);
	List<User> findUsersByRole(Role role);
	User findUserByRealName(String realName);
	List<Member> getMemberListCanBeAdded(int houseKeeperId);
	void saveAllMembers() throws FileWritingException;
	TableModel getUserTableModel() throws FileReadingException;
	TableModel getMemberEditTableModel(int hskId);
	boolean removeMemberFromHouseKeeper(int memberId) throws ObjectNotFoundException, FileWritingException;

}
