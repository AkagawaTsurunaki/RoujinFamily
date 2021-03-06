package com.github.akagawatsurunaki.roujinfamily.service;

import java.util.List;
import javax.swing.table.TableModel;
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
public interface HouseKeeperService {

	Table<Member> getMemberTable();
	void loadAllMembersFromFile() throws FileReadingException;
	List<Member> findMembersByHouseKeeperId(int houseKeeperId);
	Member findMemberById(int id) throws ObjectNotFoundException;
	boolean addMember(Member member) throws FileWritingException, CanNotMatchException;
	TableModel getMemberEditTableModel(int hskId);

}
