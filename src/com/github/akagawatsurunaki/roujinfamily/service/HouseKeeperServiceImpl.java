package com.github.akagawatsurunaki.roujinfamily.service;

import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.dao.MemberDao;
import com.github.akagawatsurunaki.roujinfamily.dao.MemberDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Table;

public class HouseKeeperServiceImpl implements HouseKeeperService {
	
	private static HouseKeeperService instance = new HouseKeeperServiceImpl();
	private MemberDao memberDao = MemberDaoImpl.getInstance();
	
	public static HouseKeeperService getInstance() {
		if(instance == null) {
			instance = new HouseKeeperServiceImpl();
		}
		return instance;
	}
	
	private HouseKeeperServiceImpl() {};
	
	@Override
	public Table<Member> getMemberTable() {
		return memberDao.getMemberTable();
	}
	@Override
	public void loadAllMembersFromFile() throws FileReadingException {
		memberDao.loadAllMembersFromFile();
	}
	@Override
	public List<Member> findMembersByHouseKeeperId(int houseKeeperId) {
		return memberDao.findMembersByHouseKeeperId(houseKeeperId);
	}
}
