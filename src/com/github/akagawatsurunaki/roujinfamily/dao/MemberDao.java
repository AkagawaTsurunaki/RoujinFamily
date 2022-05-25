package com.github.akagawatsurunaki.roujinfamily.dao;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Table;

public interface MemberDao {
	public void initialize() throws UserInfoDataReadingException;
	public void loadAllMembersFromFile() throws UserInfoDataReadingException;
	public void saveAllMembersToFile() throws UserInfoDataWritingException;
	public Table<Member> getMemberTable();
	boolean addMember(Member newMember) throws UserInfoDataWritingException, UserInfoInvalidException;
	boolean clearMemberTable() throws UserInfoDataWritingException;
	boolean removeMember(int id) throws UserInfoDataWritingException, UserNotFoundException;
	Member findMemberByRealName(String realName) throws UserNotFoundException;
	Member findMemberById(int id) throws UserNotFoundException;
}
