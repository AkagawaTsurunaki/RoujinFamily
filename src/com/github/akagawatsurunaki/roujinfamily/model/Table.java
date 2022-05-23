package com.github.akagawatsurunaki.roujinfamily.model;

import java.util.List;

public class Table <T> {

	int idCount;
	List<T> data;
	
	public Table(int idCount, List<T> data) {
		this.idCount = idCount;
		this.data = data;
	}
	
	public List<T> getDataList() {
		return data;
	}
	
}
