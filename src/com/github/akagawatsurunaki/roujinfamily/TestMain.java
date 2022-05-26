package com.github.akagawatsurunaki.roujinfamily;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.dao.MemberDao;
import com.github.akagawatsurunaki.roujinfamily.dao.MemberDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;


public class TestMain {

	public static void main(String[] args) throws CanNotMatchException {
		
		System.out.println("Start!");
		
		LocalDate birth = LocalDate.now();
		
		Member m1 = new Member(
				0,
				"王麻子",
				Gender.FEMALE,
				birth,
				"13918181818",
				120
				);
		
		Member m2 = new Member(
				0,
				"狗蛋",
				Gender.MALE,
				birth,
				"13918181818",
				120
				);
		Member m3 = new Member(
				0,
				"不能",
				Gender.MALE,
				birth,
				"13918181818",
				180
				);
		
		MemberDao dao = MemberDaoImpl.getInstance();
		
		
		
		try {
			
			dao.loadAllMembersFromFile();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//List<Member> members = MemberDaoImpl.getInstance().findMembersByHouseKeeperId(120);
		
	};



}
