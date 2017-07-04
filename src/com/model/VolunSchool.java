package com.model;

public class VolunSchool {

	private int vsId;
	private String id;
	private String rId;
	private int nameDuty;
	
	@Override
	public String toString() {
		return "VolunSchool [vsId=" + vsId + ", id=" + id + ", rId=" + rId + ", nameDuty=" + nameDuty + "]";
	}
	public int getVsId() {
		return vsId;
	}
	public void setVsId(int vsId) {
		this.vsId = vsId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public int getNameDuty() {
		return nameDuty;
	}
	public void setNameDuty(int nameDuty) {
		this.nameDuty = nameDuty;
	}
	
}
