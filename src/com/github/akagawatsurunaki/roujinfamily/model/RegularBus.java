package com.github.akagawatsurunaki.roujinfamily.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;

public class RegularBus {

	// #region Peoperties
	
	private int id;
	public String routeCode;
	private String routeName;
	private RouteType routeType;
	private RouteDirection routeDirection;
	private DayOfWeek operateDate;
	private LocalTime departureTime;
	private LocalTime terminateTime;
	private String notes;
	private List<Member> passengerList;

	// #endregion
	
	// #region Constructors
	
	public RegularBus(int id,
			String routeCode,
			String routeName,
			RouteType routeType,
			RouteDirection routeDirection,
			DayOfWeek operateDate,
			LocalTime departureTime,
			LocalTime terminateTime,
			String notes,
			List<Member> passengerList
			) throws CanNotMatchException {
		setId(id);
		setRouteCode(routeCode);
		setRouteName(routeName);
		setRouteType(routeType);
		setRouteDirection(routeDirection);
		setOperateDate(operateDate);
		setDepartureTime(departureTime);
		setTerminateTime(terminateTime);
		
		if(departureTime.isAfter(terminateTime)) {
			throw new CanNotMatchException("����ʱ�䲻�����ڽ�ֹʱ�䡣", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
		
		setNotes(notes);
		setPassengerList(passengerList);
	}
	
	public RegularBus(int id,
			String routeCode,
			String routeName,
			RouteType routeType,
			RouteDirection routeDirection,
			DayOfWeek operateDate,
			LocalTime departureTime,
			LocalTime terminateTime,
			String notes
			) throws CanNotMatchException {
		setId(id);
		setRouteCode(routeCode);
		setRouteName(routeName);
		setRouteType(routeType);
		setRouteDirection(routeDirection);
		setOperateDate(operateDate);
		setDepartureTime(departureTime);
		setTerminateTime(terminateTime);
		
		if(departureTime.isAfter(terminateTime)) {
			throw new CanNotMatchException("����ʱ�䲻�����ڽ�ֹʱ�䡣", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
		
		setNotes(notes);
		setPassengerList(null);
	}
	
	// #endregion
	
	
	// #region Getters
	

	public int getId() {
		return id;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public String getRouteName() {
		return routeName;
	}

	public RouteType getRouteType() {
		return routeType;
	}

	public RouteDirection getRouteDirection() {
		return routeDirection;
	}

	public DayOfWeek getOperateDate() {
		return operateDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public LocalTime getTerminateTime() {
		return terminateTime;
	}

	public String getNotes() {
		return notes;
	}
	
	public List<Member> getPassengerList() {
		return passengerList;
	}

	// #endregion
	
	// #region Setters
	
	public void setId(int id) {
		this.id = id;
	}

	public void setRouteCode(String routeCode) throws CanNotMatchException {
		
		
		if(routeCode.length() > Constants.MAX_ROUTECODE_LENGTH || 
				routeCode.length() < Constants.MIN_ROUTECODE_LENGTH) {
			throw new CanNotMatchException("��·���볤�ȱ������" + 
											Constants.MIN_ROUTECODE_LENGTH + 
											"�ֵ�����" + 
											Constants.MAX_ROUTECODE_LENGTH + 
											"�֡�", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
		this.routeCode = routeCode;
	}

	public void setRouteName(String routeName) throws CanNotMatchException {
		if(routeName.length() > Constants.MAX_ROUTENAME_LENGTH || 
				routeName.length() < Constants.MIN_ROUTENAME_LENGTH) {
			throw new CanNotMatchException("·�����Ƴ��ȱ������" +
											Constants.MIN_ROUTENAME_LENGTH + 
											"�ֵ�����" + 
											Constants.MAX_ROUTENAME_LENGTH + 
											"�֡�", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
		this.routeName = routeName;
	}

	public void setRouteType(RouteType routeType) {
		this.routeType = routeType;
	}

	public void setRouteDirection(RouteDirection routeDirection) {
		this.routeDirection = routeDirection;
	}

	public void setOperateDate(DayOfWeek operateDate) {
		this.operateDate = operateDate;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public void setTerminateTime(LocalTime terminateTime) throws CanNotMatchException {
		this.terminateTime = terminateTime;
		if(departureTime.isAfter(terminateTime)) {
			throw new CanNotMatchException("����ʱ�䲻�����ڽ�ֹʱ�䡣", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
	}

	public void setNotes(String notes) throws CanNotMatchException {
		if(notes.length() > Constants.MAX_NOTE_LENGTH || notes.length() < Constants.MIN_NOTE_LENGTH) {
			throw new CanNotMatchException("��ע���ܶ���" + Constants.MAX_NOTE_LENGTH + "�֡�", "�Ƿ�����", "�ô�������ģ�Ͳ㷢��ġ�");
		}
		this.notes = notes;
	}
	
	public void setPassengerList(List<Member> passengerList) {
		
		if(passengerList == null || passengerList.isEmpty()) {
			passengerList = new ArrayList<Member>();
		}
		
		this.passengerList = passengerList;
	}
	
	// #endregion
	
	public boolean addPassengerIntoList(Member member) throws CanNotMatchException {
		
		if(LocalDate.now().getDayOfWeek().equals(operateDate)) {
			if(LocalTime.now().isBefore(terminateTime)) {
				return passengerList.add(member);
			}
			else {
				throw new CanNotMatchException("�Ǽ�ʱ������ڸð೵����ʱ��֮ǰ��", "�Ǽ�ʧ��", "�ô�������ģ�Ͳ㷢���");
			}
		}
		else {
			throw new CanNotMatchException("�˰೵������" + operateDate + "ʱ���ܱ��Ǽǡ�", "�Ǽ�ʧ��", "�ô�������ģ�Ͳ㷢���");

		}
		
	}
	
	public boolean removePassengerFromList(Member member) {
		return passengerList.remove(member);
	}

	public boolean removePassengerFromList(int memberId) {
		for(Member member : passengerList) {
			if(member.getId() == memberId) {
				passengerList.remove(member);
				return true;
			}
		}
		return false;
	}

	
	public String[] toStringArray() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		String[] ret = {
				Integer.toString(id), 
				routeCode, 
				routeName, 
				routeType.toString(),
				routeDirection.toString(),
				operateDate.toString(),
				formatter.format(departureTime),
				formatter.format(terminateTime),
				Integer.toString(passengerList.size())
				};
		
		return ret;
	}
}
