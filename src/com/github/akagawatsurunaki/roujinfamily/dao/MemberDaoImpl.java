package com.github.akagawatsurunaki.roujinfamily.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Constants;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.util.FileUtil;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;

public class MemberDaoImpl implements MemberDao {

	// #region Properties

	private static MemberDao instance = new MemberDaoImpl();

	private static final String filePath = ".\\save\\Members.json";
	// "C:\\Users\\96514\\Desktop\\save\\Members.json";

	private Table<Member> memberTable;

	// #endregion

	// #region Constructors and Instance Getter

	private MemberDaoImpl() {
	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDaoImpl();
		}
		return instance;
	}

	// #endregion

	// #region Some Menthods
	@Override
	public void initialize() throws FileReadingException {
		loadAllMembersFromFile();
	}

	@Override
	public void loadAllMembersFromFile() throws FileReadingException {
		try {
			String str = FileUtil.readFile(filePath);
			this.memberTable = GsonUtil.fromJsonToMemberTable(str);
		} catch (IOException e) {
			throw new FileReadingException("会员信息文件无法读取。", "读取文件失败", "该错误是由数据层发起的。");
		}
	}

	@Override
	public void saveAllMembersToFile() throws FileWritingException {
		String dataStr = GsonUtil.fromMemberTableToJson(memberTable);
		try {
			FileUtil.writeFile(filePath, dataStr);
		} catch (IOException e) {
			throw new FileWritingException("会员信息文件无法保存。", "写入文件失败", "该错误是由数据层发起的。");
		}
	}

	@Override
	public Table<Member> getMemberTable() {
		return this.memberTable;
	}

	@Override
	public List<Member> getMemberListCanBeAdded(int houseKeeperId) {

		List<Member> memberTable = getMemberTable().getData();
		List<Member> ret = new ArrayList<Member>();
		for (Member member : memberTable) {
			if (houseKeeperId == member.getHouseKeeperId()) {
				continue;
			}
			ret.add(member);
		}
		return ret;
	}

	@Override
	public boolean addMember(Member newMember) throws FileWritingException, CanNotMatchException {

		if (this.memberTable == null || memberTable.getData().isEmpty()) {
			List<Member> list = new ArrayList<Member>();
			this.memberTable = new Table<Member>(0, list);
			this.memberTable.getData().add(newMember);
			return true;
		} else {
			List<Member> memberList = getMemberTable().getData();
			for (Member member : memberList) {
				if (member.getId() == newMember.getId()) {
					member.setRealName(newMember.getRealName());
					member.setTelNumber(newMember.getTelNumber());
					member.setGender(newMember.getGender());
					member.setBirthday(newMember.getBirthday());
					member.setHouseKeeperId(newMember.getHouseKeeperId());
					saveAllMembersToFile();
					return true;
				}
			}
		}
		newMember.setId(this.memberTable.getIdCount() + 1);
		this.memberTable.addDataSeg(newMember);
		saveAllMembersToFile();
		return true;

	}

	@Override
	public boolean clearMemberTable() throws FileWritingException {
		memberTable.clear();
		saveAllMembersToFile();
		return true;
	}

	@Override
	public boolean removeMember(int id) throws FileWritingException, ObjectNotFoundException {
		memberTable.removeDataSeg(findMemberById(id));
		saveAllMembersToFile();
		return true;
	}
	
	@Override 
	public boolean removeMemberFromHouseKeeper(int memberId) throws ObjectNotFoundException, FileWritingException {
		Member member = findMemberById(memberId);
		member.setHouseKeeperId(Constants.DEFAULT_OBJECT_ID);
		saveAllMembersToFile();
		return true;
	}

	@Override
	public Member findMemberByRealName(String realName) throws ObjectNotFoundException {
		List<Member> memberList = getMemberTable().getData();
		for (Member member : memberList) {
			if (realName.equals(member.getRealName())) {
				return member;
			}
		}
		throw new ObjectNotFoundException("不存在真实姓名为“" + realName + "”的会员。", "找不到对象", "该错误是由数据层发起的。");
	}

	@Override
	public Member findMemberById(int id) throws ObjectNotFoundException {
		List<Member> memberList = getMemberTable().getData();
		for (Member member : memberList) {
			if (member.getId() == id) {
				return member;
			}
		}
		throw new ObjectNotFoundException("不存在ID为“" + id + "”的会员。", "找不到对象", "该错误是由数据层发起的。");
	}

	@Override
	public List<Member> findMembersByHouseKeeperId(int houseKeeperId) {
		List<Member> memberList = memberTable.getData();
		List<Member> ret = new ArrayList<Member>();
		for (Member member : memberList) {
			if (member.getHouseKeeperId() == houseKeeperId) {
				ret.add(member);
			}
		}
		return ret;
	}

	@Override
	public TableModel getMemberEditTableModel(int hskId) {
		List<Member> segMemberList = findMembersByHouseKeeperId(hskId);
		String[] tableTitle = { "身份标识", "姓名", "性别", "出生日期", "电话" };
		String[][] tableContent = new String[segMemberList.size()][tableTitle.length];
		int i = 0;
		for (Member member : segMemberList) {
			tableContent[i] = Arrays.copyOf(member.toStringArray(), tableTitle.length);
			i++;
		}
		TableModel tableModel = new DefaultTableModel(tableContent, tableTitle) {
			private static final long serialVersionUID = 1L;

			// The table can not be operated.
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		return tableModel;
	}

	// #endregion
}
