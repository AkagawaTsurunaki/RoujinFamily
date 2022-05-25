package com.github.akagawatsurunaki.roujinfamily.dao;

import java.io.IOException;
import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.util.FileUtil;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;

public class MemberDaoImpl implements MemberDao {

	// #region Properties
	
	private static MemberDao instance = new MemberDaoImpl();
	private static final String filePath = "C:\\Users\\96514\\Desktop\\save\\Members.json";
	
	private Table<Member> memberTable;
	
	// #endregion 
	
	// #region Constructors and Instance Getter
	
	private MemberDaoImpl() {}
	
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDaoImpl();
		}
		return instance;
	}
	
	// #endregion
	
	// #region Some Menthods
	@Override
	public void initialize() throws UserInfoDataReadingException {
		instance.loadAllMembersFromFile();
	}
	@Override
	public void loadAllMembersFromFile() throws UserInfoDataReadingException {
		try {
			String str = FileUtil.readFile(filePath);
			this.memberTable = GsonUtil.fromJsonToMemberTable(str);
		} catch (IOException e) {
			throw new UserInfoDataReadingException("读取用户信息文件失败。");
		}
	}
	@Override
	public void saveAllMembersToFile() throws UserInfoDataWritingException {
		String dataStr = GsonUtil.fromMemberTableToJson(memberTable);
		try {
			FileUtil.writeFile(filePath, dataStr);
		} catch (IOException e) {
			throw new UserInfoDataWritingException("写入用户信息文件失败。");
		}
	}
	@Override
	public Table<Member> getMemberTable() {
		return this.memberTable;
	}
	@Override
	public boolean addMember(Member newMember) throws UserInfoDataWritingException, UserInfoInvalidException {
		
		List<Member> memberList = getMemberTable().getData();
		for (Member member : memberList) {
			if(member.getId() == newMember.getId()) {
				
				member.setRealName(newMember.getRealName());
				member.setTelNumber(newMember.getTelNumber());;
				member.setGender(newMember.getGender());
				member.setBirthday(newMember.getBirthday());
				member.setHouseKeeperId(newMember.getHouseKeeperId());
				return true;
				
			}
		}
		
		newMember.setId(this.memberTable.getIdCount() + 1);
		this.memberTable.addDataSeg(newMember);
		saveAllMembersToFile();
		return false;
	}
	
	@Override
	public boolean clearMemberTable() throws UserInfoDataWritingException {
		memberTable.clear();
		saveAllMembersToFile();
		return true;
	}

	@Override
	public boolean removeMember(int id) throws UserInfoDataWritingException, UserNotFoundException {
		memberTable.removeDataSeg(findMemberById(id)); 
		saveAllMembersToFile();
		return true; 
		
	}
	@Override
	public Member findMemberByRealName(String realName) throws UserNotFoundException {
		List<Member> memberList = getMemberTable().getData();
		for (Member member : memberList) {
			if (realName.equals(member.getRealName())) {
				return member;
			}
		}
		throw new UserNotFoundException("用户不存在。");
	}
	@Override
	public Member findMemberById(int id) throws UserNotFoundException {
		List<Member> memberList = getMemberTable().getData();
		for (Member member : memberList) {
			if (member.getId() == id) {
				return member;
			}
		}
		throw new UserNotFoundException("用户不存在。");
	}
	
	// #endregion
}
