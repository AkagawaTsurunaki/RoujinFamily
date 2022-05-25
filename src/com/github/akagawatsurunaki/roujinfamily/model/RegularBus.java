package com.github.akagawatsurunaki.roujinfamily.model;

import java.util.List;

public class RegularBus {
    
	private RouteInfo RouteInfo;
    private List<Member> passengerList;
    
	public RouteInfo getRouteInfo() {
		return RouteInfo;
	}
	public List<Member> getPassengerList() {
		return passengerList;
	}
	public void setRouteInfo(RouteInfo routeInfo) {
		RouteInfo = routeInfo;
	}
	public void setPassengerList(List<Member> passengerList) {
		this.passengerList = passengerList;
	}
    
    
}
