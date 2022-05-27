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
import com.github.akagawatsurunaki.roujinfamily.service.LogisticsManagementService;
import com.github.akagawatsurunaki.roujinfamily.service.LogisticsManagementServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;


public class TestMain {

	public static void main(String[] args) throws CanNotMatchException {
		
		System.out.println("Start!");
		
		LocalDate birth = LocalDate.now();
		
		LogisticsManagementService se = LogisticsManagementServiceImpl.getInstance();
		
		
		
	};



}
