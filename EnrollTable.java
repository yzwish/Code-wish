package com.model;

public class EnrollTable {

	
	private String id;
	private String enrollTime;
	
	//添加
	private String title;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEnrollTime() {
		return enrollTime;
	}
	public void setEnrollTime(String enrollTime) {
		this.enrollTime = enrollTime;
	}
	@Override
	public String toString() {
		return "EnrollTable [id=" + id + ", enrollTime=" + enrollTime + ", title=" + title + "]";
	}
	
	
	
	
}
