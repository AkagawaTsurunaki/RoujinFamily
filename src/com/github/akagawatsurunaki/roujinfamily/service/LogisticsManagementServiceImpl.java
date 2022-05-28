package com.github.akagawatsurunaki.roujinfamily.service;

import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.dao.RegularBusDao;
import com.github.akagawatsurunaki.roujinfamily.dao.RegularBusDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.RegularBus;
import com.github.akagawatsurunaki.roujinfamily.model.Table;

public class LogisticsManagementServiceImpl implements LogisticsManagementService {

	private static LogisticsManagementServiceImpl instance = new LogisticsManagementServiceImpl();
	
	private static RegularBusDao dao = RegularBusDaoImpl.getInstance();
	
	public static LogisticsManagementService getInstance() {
		
		if(instance == null) {
			instance = new LogisticsManagementServiceImpl();
		}
		return instance;
	}
	
	private LogisticsManagementServiceImpl() {}
	
	@Override
	public boolean loadAllRegularBuses() throws FileReadingException {
		return dao.loadAllRegularBuses();
	}
	
	// #region TableContentData Getters
	@Override
	public String[] getRegularBusTableTitle() {
		return dao.getRegularBusTableTitle();
	}
	@Override
    public String[][] getRegularBusTableAsStringArray() throws FileReadingException {
		return dao.getRegularBusTableAsStringArray();
	}

	@Override
	public boolean saveAllRegularBuses() throws FileWritingException {
		return dao.saveAllRegularBuses();
	}

	@Override
	public List<Member> getPassengerListInRegularBus(int regularBusId) throws ObjectNotFoundException {
		return dao.getPassengerListInRegularBus(regularBusId);
	}

	@Override
	public List<Member> getPassengerListInRegularBus(RegularBus regularBus) throws ObjectNotFoundException {
		return dao.getPassengerListInRegularBus(regularBus);
	}
	
	@Override
	public Table<RegularBus> getRegularBusTable() {
		return dao.getRegularBusTable();
	}
	

	@Override
	public Member findPassengerInRegularBus(Member member, RegularBus regularBus) throws ObjectNotFoundException {
		return dao.findPassengerInRegularBus(member, regularBus);
	}

	@Override
	public Member findPassengerInRegularBus(int memberId, int regularBusId) throws ObjectNotFoundException {
		return dao.findPassengerInRegularBus(memberId, regularBusId);
	}
	
	@Override
	public boolean addRegularBus(RegularBus newRegularBus) throws FileWritingException, CanNotMatchException {
		return dao.addRegularBus(newRegularBus);
	}

	@Override
	public boolean addPassengerIntoRegularBus(Member member, RegularBus regularBus) throws FileWritingException, CanNotMatchException {
		return dao.addPassengerIntoRegularBus(member, regularBus);
	}

	@Override
	public boolean addPassengerIntoRegularBus(Member member, int regularBusId)
			throws ObjectNotFoundException, FileWritingException, CanNotMatchException {
		return dao.addPassengerIntoRegularBus(member, regularBusId);
	}

	@Override
	public boolean removePassengerFromRegularBus(Member member, RegularBus regularBus) throws FileWritingException {
		return dao.removePassengerFromRegularBus(member, regularBus);
	}

	@Override
	public boolean removePassengerFromRegularBus(Member member, int regularBusId)
			throws ObjectNotFoundException, FileWritingException {
		return dao.removePassengerFromRegularBus(member, regularBusId);
	}

	@Override
	public RegularBus findRegularBus(RegularBus regularBus) throws ObjectNotFoundException {
		return dao.findRegularBus(regularBus);
	}

	@Override
	public RegularBus findRegularBus(int regularBusId) throws ObjectNotFoundException {
		return dao.findRegularBus(regularBusId);
	}
	@Override
	public boolean removeRegularBus(int id) throws FileWritingException {
		return dao.removeRegularBus(id);
	}
	
	@Override
	public void editTerminateTime(int id, String time) throws ObjectNotFoundException, CanNotMatchException, FileWritingException {
		dao.editTerminateTime(id, time);
		
	}
	// #endregion
}
