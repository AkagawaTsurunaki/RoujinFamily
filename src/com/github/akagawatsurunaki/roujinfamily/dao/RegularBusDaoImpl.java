package com.github.akagawatsurunaki.roujinfamily.dao;

import java.io.IOException;
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
			throw new FileReadingException("班车信息文件无法读取。", "读取文件失败", "该错误是由数据层发起的。");
		}
    }
	
    @Override
    public boolean saveAllRegularBuses() throws FileWritingException {
		String dataStr = GsonUtil.fromRegularBusTableToJson(regularBusTable);
		try {
			FileUtil.writeFile(filePath, dataStr);
			return true;
		} catch (IOException e) {
			throw new FileWritingException("会员信息文件无法保存。", "写入文件失败", "该错误是由数据层发起的。");
		}
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
    	throw new ObjectNotFoundException("不存在ID为“" + regularBusId + "”的班车。", "找不到对象", "该错误是由数据层发起的。");
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
    	throw new ObjectNotFoundException("不存在ID为“" + regularBusId + "”的预约者。", "找不到对象", "该错误是由数据层发起的。");
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
    	String[] tableTitle = { "身份标识", "线路代码", "路线名称", 
    			"方向", "运营日期", "运营时段", "发车时间", "截止时间", "预约人数" };
    	return tableTitle;
    }
    @Override
    public String[][] getRegularBusTableAsStringArray() {
    	
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
