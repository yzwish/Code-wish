package com.model;

import java.sql.Timestamp;

public class VisitsNum {

	private Timestamp time;
	private int pageView;
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getPageView() {
		return pageView;
	}
	public void setPageView(int pageView) {
		this.pageView = pageView;
	}
	
	
}
