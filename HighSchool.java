package com.model;

public class HighSchool {

	private String provinceId;
	private String hId;
	private String hname;
	
	
	public HighSchool() {
	}
	public HighSchool(String hId, String hname) {
		super();
		this.hId = hId;
		this.hname = hname;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String gethId() {
		return hId;
	}
	public void sethId(String hId) {
		this.hId = hId;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	@Override
	public String toString() {
		return "HighSchool [provinceId=" + provinceId + ", hId=" + hId + ", hname=" + hname + "]";
	}
	
	
}
