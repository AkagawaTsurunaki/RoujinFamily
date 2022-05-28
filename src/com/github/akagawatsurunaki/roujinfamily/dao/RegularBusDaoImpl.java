package com.github.akagawatsurunaki.roujinfamily.dao;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.RegularBus;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.util.FileUtil;
import com.github.akagawatsurunaki.roujinfamily.util.GlobalFormatter;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;

public class RegularBusDaoImpl implements RegularBusDao {
	
	Table<RegularBus> regularBusTable;
	
	private static RegularBusDao instance = new RegularBusDaoImpl();
	
	private static final String filePath = "C:\\Users\\96514\\Desktop\\save\\RegularBus.json";
	
	// #region Constructors and Instance Getters
	
	public static RegularBusDao getInstance() {
		if (instance == null) {
			instance = new RegularBusDaoImpl();
		}
		return instance;
	}
	
	private RegularBusDaoImpl() {}
	

	
	// #endregion
	
	// #region Load and Save Methods
	
	@Override
    public boolean loadAllRegularBuses() throws FileReadingException {
    	String str;
		try {
			str = FileUtil.readFile(filePath);
			this.regularBusTable = GsonUtil.fromJsonToRegularBusTable(str);
			if(this.regularBusTable == null) {
				return false;
			}
			return true;
		} catch (IOException e) {
			throw new FileReadingException("�೵��Ϣ�ļ��޷���ȡ��", "��ȡ�ļ�ʧ��", "�ô����������ݲ㷢��ġ�");
		}
    }
	
    @Override
    public boolean saveAllRegularBuses() throws FileWritingException {
		String dataStr = GsonUtil.fromRegularBusTableToJson(regularBusTable);
		try {
			FileUtil.writeFile(filePath, dataStr);
			return true;
		} catch (IOException e) {
			throw new FileWritingException("��Ա��Ϣ�ļ��޷����档", "д���ļ�ʧ��", "�ô����������ݲ㷢��ġ�");
		}
    }
    
    // #endregion
    
    // #region Add Bus Methods
    
    @Override
    public boolean addRegularBus(RegularBus newRegularBus) throws FileWritingException, CanNotMatchException {
    	
		List<RegularBus> busList = regularBusTable.getData();
		
		if(busList == null || busList.isEmpty()) {
			for (RegularBus bus : busList) {
				if(bus.getId() == newRegularBus.getId()) {
					bus.setDepartureTime(newRegularBus.getDepartureTime());
					bus.setNotes(newRegularBus.getNotes());
					bus.setOperateDate(newRegularBus.getOperateDate());
					bus.setPassengerList(newRegularBus.getPassengerList());
					bus.setRouteCode(newRegularBus.getRouteCode());
					bus.setRouteDirection(newRegularBus.getRouteDirection());
					bus.setRouteName(newRegularBus.getRouteName());
					bus.setRouteType(newRegularBus.getRouteType());
					bus.setTerminateTime(newRegularBus.getTerminateTime());
					return true;
				}
			}
		}

		newRegularBus.setId(regularBusTable.getIdCount() + 1);
		regularBusTable.addDataSeg(newRegularBus);
		saveAllRegularBuses();

		return true;
    }
    @Override
    public void editTerminateTime(int id, String time) throws ObjectNotFoundException, CanNotMatchException, FileWritingException {
    	LocalTime newtime = LocalTime.parse(time, GlobalFormatter.timeFormatter);
    	RegularBus bus = findRegularBus(id);
    	bus.setTerminateTime(newtime);
    	saveAllRegularBuses();
    }
    
    // #endregion
    
    // #region Get Bus Methods
    @Override
    public Table<RegularBus> getRegularBusTable(){
    	return regularBusTable;
    }
    
    
    
    // #endregion
    
    // #region Add Passenger Methods
    @Override
    public boolean addPassengerIntoRegularBus(Member member, RegularBus regularBus) throws FileWritingException, CanNotMatchException { 
    	boolean flag = regularBus.addPassengerIntoList(member);
    	saveAllRegularBuses();
    	return flag;
    }
    
    @Override
    public boolean addPassengerIntoRegularBus(Member member, int regularBusId) throws ObjectNotFoundException, FileWritingException, CanNotMatchException { 
    	RegularBus bus = findRegularBus(regularBusId);
    	boolean flag = bus.addPassengerIntoList(member);
    	saveAllRegularBuses();
    	return flag;
    }
    
    // #endregion
    
    // #region Remove Methods
    
    @Override
    public boolean removePassengerFromRegularBus(Member member, RegularBus regularBus) throws FileWritingException {
    	boolean flag = regularBus.removePassengerFromList(member);
    	saveAllRegularBuses();
    	return flag;
    }
    
    @Override
    public boolean removePassengerFromRegularBus(Member member, int regularBusId) throws ObjectNotFoundException, FileWritingException {
    	RegularBus bus = findRegularBus(regularBusId);
    	boolean flag = bus.removePassengerFromList(regularBusId);
    	saveAllRegularBuses();
    	return flag;
    }
    
    @Override
    public boolean removeRegularBus(int id) throws FileWritingException {
    	
    	for(RegularBus bus: regularBusTable.getData()) {
    		if(bus.getId() == id) {
        		regularBusTable.removeDataSeg(bus);
        		saveAllRegularBuses();
        		return true;
    		}
    		
    	}
		return false;
    	
    }
    
    
    // #endregion
    
    // #region Find Bus Methods
    
    @Override
    public RegularBus findRegularBus(RegularBus regularBus) throws ObjectNotFoundException {
    	return findRegularBus(regularBus.getId());
    }
    @Override
    public RegularBus findRegularBus(int regularBusId) throws ObjectNotFoundException  {
    	for(RegularBus bus : this.regularBusTable.getData()) {
    		if(regularBusId == bus.getId()) {
    			return bus;
    		}
    	}
    	throw new ObjectNotFoundException("������IDΪ��" + regularBusId + "���İ೵��", "�Ҳ�������", "�ô����������ݲ㷢��ġ�");
    }
    
    // #endregion
    
    // #region Find Single Passenger in Bus Methods 
    @Override
    public Member findPassengerInRegularBus(Member member, RegularBus regularBus) throws ObjectNotFoundException{
    	return findPassengerInRegularBus(member.getId(), regularBus.getId());
    }
    @Override
    public Member findPassengerInRegularBus(int memberId, int regularBusId) throws ObjectNotFoundException {
    	RegularBus bus = findRegularBus(regularBusId);
    	List<Member> memberList = bus.getPassengerList();
    	for(Member member : memberList) {
    		if(member.getId() == memberId) {
    			return member;
    		}
    	}
    	throw new ObjectNotFoundException("������IDΪ��" + regularBusId + "����ԤԼ�ߡ�", "�Ҳ�������", "�ô����������ݲ㷢��ġ�");
    }
  
    // #endregion
    
    // #region Get Passenger List Methods
    @Override
    public List<Member> getPassengerListInRegularBus(RegularBus regularBus) throws ObjectNotFoundException {
    	
    	return getPassengerListInRegularBus(regularBus.getId());
    }
    @Override
    public List<Member> getPassengerListInRegularBus(int regularBusId) throws ObjectNotFoundException {
    	
    	RegularBus bus = findRegularBus(regularBusId);
    	return bus.getPassengerList();
    }
    
    // #endregion
    
    // #region TableContentData Getters
    @Override
    public String[] getRegularBusTableTitle() {
    	String[] tableTitle = { "��ݱ�ʶ", "��·����", "·������", 
    			"����", "��Ӫ����", "��Ӫʱ��", "����ʱ��", "��ֹʱ��", "ԤԼ����" };
    	return tableTitle;
    }
    @Override
    public String[][] getRegularBusTableAsStringArray() throws FileReadingException {
    	
    	loadAllRegularBuses();
    	List<RegularBus> busList = regularBusTable.getData();
    	int len = getRegularBusTableTitle().length;
    	String[][] tableContent = new String[busList.size()][len];
    	
    	int i = 0;
    	
		for (RegularBus bus : busList) {
			tableContent[i] = Arrays.copyOf(bus.toStringArray(), len);
			i++;
		}
		
		return tableContent;

    }

    // #endregion
	

}
