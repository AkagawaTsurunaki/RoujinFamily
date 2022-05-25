package com.github.akagawatsurunaki.roujinfamily.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class RouteInfo {
	
    private int id;
    private String routeCode;
    private String routeName;
    private RouteType routeType;
    private RouteDirection routeDirection;
    private LocalDate operateDate;
    private LocalTime departureTime;
    private LocalTime terminateTime;
    private String notes;
    
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
	public LocalDate getOperateDate() {
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
	public void setId(int id) {
		this.id = id;
	}
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public void setRouteType(RouteType routeType) {
		this.routeType = routeType;
	}
	public void setRouteDirection(RouteDirection routeDirection) {
		this.routeDirection = routeDirection;
	}
	public void setOperateDate(LocalDate operateDate) {
		this.operateDate = operateDate;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public void setTerminateTime(LocalTime terminateTime) {
		this.terminateTime = terminateTime;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
    
    
}
