package com.github.akagawatsurunaki.roujinfamily.dao;

import java.util.List;

import javax.swing.table.TableModel;

import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Table;

public interface MemberDao {
	public void initialize() throws FileReadingException;
	public void loadAllMembersFromFile() throws FileReadingException;
	public void saveAllMembersToFile() throws FileWritingException;
	public Table<Member> getMemberTable();
	boolean addMember(Member newMember) throws FileWritingException, CanNotMatchException;
	boolean clearMemberTable() throws FileWritingException;
	boolean removeMember(int id) throws FileWritingException, ObjectNotFoundException;
	Member findMemberByRealName(String realName) throws ObjectNotFoundException;
	Member findMemberById(int id) throws ObjectNotFoundException;
	List<Member> findMembersByHouseKeeperId(int houseKeeperId);
	List<Member> getMemberListCanBeAdded(int houseKeeperId);
	TableModel getMemberEditTableModel(int hskId);
	boolean removeMemberFromHouseKeeper(int memberId) throws ObjectNotFoundException, FileWritingException;
}
