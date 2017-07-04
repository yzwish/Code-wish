package com.model;

import java.io.Serializable;

public class ActivityAnswer implements Serializable {
	private static final long serialVersionUID = -3183859944094049354L;

	private int acAnswerId;
	private int activityId;
	private int acQuestionId;
	private String acAnContext;
	private String acAnTime;
	
	
	public int getAcAnswerId() {
		return acAnswerId;
	}
	public void setAcAnswerId(int acAnswerId) {
		this.acAnswerId = acAnswerId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getAcQuestionId() {
		return acQuestionId;
	}
	public void setAcQuestionId(int acQuestionId) {
		this.acQuestionId = acQuestionId;
	}
	public String getAcAnContext() {
		return acAnContext;
	}
	public void setAcAnContext(String acAnContext) {
		this.acAnContext = acAnContext;
	}
	public String getAcAnTime() {
		return acAnTime;
	}
	public void setAcAnTime(String acAnTime) {
		this.acAnTime = acAnTime;
	}
	
	
}
