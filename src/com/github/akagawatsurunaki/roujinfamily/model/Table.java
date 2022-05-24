package com.github.akagawatsurunaki.roujinfamily.model;

import java.util.List;

public class Table <T> {

	private int idCount;
	private List<T> data;
	
	// #region Constructor and setters and getters
	public Table(int idCount, List<T> data) {
		this.idCount = idCount;
		this.data = data;
	}
	public void setIdCount(int idCount) {
		this.idCount = idCount;
	}
	public int getIdCount() {
		return idCount;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	// #endregion
	
	public void addDataSeg(T seg) {
		idCount++;
		data.add(seg);
	}
	
	public boolean removeDataSeg(T seg) {
		for(T t : data) {
			if(t.equals(seg)) {
				data.remove(t);
				return true;
			}
		}
		return false;
	}
	
	public void clear() {
		this.idCount = 0;
		data.clear();
	}
	
}
