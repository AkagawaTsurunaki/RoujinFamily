package com.github.akagawatsurunaki.roujinfamily.service;

import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.RegularBus;

public interface LogisticsManagementService {

	public String[] getRegularBusTableTitle();

	String[][] getRegularBusTableAsStringArray();

	boolean loadAllRegularBuses() throws FileReadingException;

	boolean saveAllRegularBuses() throws FileWritingException;
	
	List<Member> getPassengerListInRegularBus(int regularBusId) throws ObjectNotFoundException;

	List<Member> getPassengerListInRegularBus(RegularBus regularBus) throws ObjectNotFoundException;

	Member findPassengerInRegularBus(Member member, RegularBus regularBus) throws ObjectNotFoundException;

	Member findPassengerInRegularBus(int memberId, int regularBusId) throws ObjectNotFoundException;

	boolean addPassengerIntoRegularBus(Member member, RegularBus regularBus) throws FileWritingException, CanNotMatchException;

	boolean addPassengerIntoRegularBus(Member member, int regularBusId)
			throws ObjectNotFoundException, FileWritingException, CanNotMatchException;

	boolean removePassengerFromRegularBus(Member member, RegularBus regularBus) throws FileWritingException;

	boolean removePassengerFromRegularBus(Member member, int regularBusId)
			throws ObjectNotFoundException, FileWritingException;

	RegularBus findRegularBus(RegularBus regularBus) throws ObjectNotFoundException;

	RegularBus findRegularBus(int regularBusId) throws ObjectNotFoundException;
	
	
}
