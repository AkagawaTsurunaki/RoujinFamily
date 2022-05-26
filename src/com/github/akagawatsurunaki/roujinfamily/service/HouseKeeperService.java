package com.github.akagawatsurunaki.roujinfamily.service;

import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Table;

public interface HouseKeeperService {

	Table<Member> getMemberTable();

	void loadAllMembersFromFile() throws FileReadingException;

	List<Member> findMembersByHouseKeeperId(int houseKeeperId);

}
