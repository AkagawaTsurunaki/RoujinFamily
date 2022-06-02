package com.github.akagawatsurunaki.roujinfamily.service;

import java.util.List;

import javax.swing.table.TableModel;

import com.github.akagawatsurunaki.roujinfamily.dao.MemberDao;
import com.github.akagawatsurunaki.roujinfamily.dao.MemberDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Table;

/**
 * @author Akagawa Tsurunaki
 *
 */
public class HouseKeeperServiceImpl implements HouseKeeperService {
	
	// #region Properties
	
	private static HouseKeeperService instance = new HouseKeeperServiceImpl();
	private MemberDao memberDao = MemberDaoImpl.getInstance();

	// #endregion
	
	// #region Singlenton Getter
	
	public static HouseKeeperService getInstance() {
		if(instance == null) {
			instance = new HouseKeeperServiceImpl();
		}
		return instance;
	}
	
	private HouseKeeperServiceImpl() {};
	
	// #endregion
	
	// #region Override Methods
	
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
	
	@Override
	public Member findMemberById(int id) throws ObjectNotFoundException {
		return memberDao.findMemberById(id);
	}
	
	@Override
	public boolean addMember(Member member) throws FileWritingException, CanNotMatchException {
		return memberDao.addMember(member);
	}
	
	@Override
	public TableModel getMemberEditTableModel(int hskId) {
		return memberDao.getMemberEditTableModel(hskId);
	}
	
	// #endregion
}
