package com.model;

import java.sql.Timestamp;

public class TransRecord {

	private int trId;
	private String id;
	private Timestamp time;
	private double virtualNum;
	private int way;
	public int getTrId() {
		return trId;
	}
	public void setTrId(int trId) {
		this.trId = trId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public double getVirtualNum() {
		return virtualNum;
	}
	public void setVirtualNum(double virtualNum) {
		this.virtualNum = virtualNum;
	}
	public int getWay() {
		return way;
	}
	public void setWay(int way) {
		this.way = way;
	}
	
	
}
