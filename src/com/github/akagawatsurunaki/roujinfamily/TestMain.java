package com.github.akagawatsurunaki.roujinfamily;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.dao.MemberDao;
import com.github.akagawatsurunaki.roujinfamily.dao.MemberDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.RegularBus;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.RouteDirection;
import com.github.akagawatsurunaki.roujinfamily.model.RouteType;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.service.LogisticsManagementService;
import com.github.akagawatsurunaki.roujinfamily.service.LogisticsManagementServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.util.FileUtil;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;


public class TestMain {

	public static void main(String[] args) throws CanNotMatchException {
		
		System.out.println("Start!");
		
		LocalDate birth = LocalDate.now();
		
		LogisticsManagementService se = LogisticsManagementServiceImpl.getInstance();
		
		try {
			MemberDaoImpl.getInstance().loadAllMembersFromFile();
		} catch (FileReadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Member> mList;
		
		
		
		mList = MemberDaoImpl.getInstance().getMemberTable().getData();
		
		LocalTime dt = LocalTime.of(8, 0);
		LocalTime tt = LocalTime.of(23, 59);
		
		RegularBus bus = new RegularBus(1, 
				"9lu", 
				"1289",
				RouteType.INNER_ISLAND, 
				RouteDirection.UP, 
				DayOfWeek.FRIDAY, 
				dt, 
				tt, 
				"ceshi",
				null
				);
		
		try {
//			
//			se.loadAllRegularBuses();
//			se.addRegularBus(bus);
//			se.addPassengerIntoRegularBus(mList.get(0), bus);
//			
			System.out.println(GsonUtil.fromRegularBusToJson(bus));

			Table<RegularBus> tbMem = se.getRegularBusTable();
			List<RegularBus> busList = tbMem.getData();
			
			se.saveAllRegularBuses();
			
		} catch (FileWritingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	};



}
