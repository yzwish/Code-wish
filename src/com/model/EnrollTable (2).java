package com.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class EnrollTable implements Serializable {

	private static final long serialVersionUID = -689483647322068483L;

	private String activityId;
	private String id;
	private String enrollTime;
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
	
	
}
