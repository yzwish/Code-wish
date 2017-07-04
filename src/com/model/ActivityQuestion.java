package com.model;

import java.io.Serializable;

public class ActivityQuestion implements Serializable {
	private static final long serialVersionUID = 1796100438496986341L;
	
	//数据表
	private int acQuestionId;
	private int activityId;
	private String id;
	private String acQuContext;
	private String acQuTime;
	private int acQeStatus;
	
	//新增
	private String answer;
	
	
	
	public int getAcQuestionId() {
		return acQuestionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setAcQuestionId(int acQuestionId) {
		this.acQuestionId = acQuestionId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAcQuContext() {
		return acQuContext;
	}
	public void setAcQuContext(String acQuContext) {
		this.acQuContext = acQuContext;
	}
	public String getAcQuTime() {
		return acQuTime;
	}
	public void setAcQuTime(String acQuTime) {
		this.acQuTime = acQuTime;
	}
	public int getAcQeStatus() {
		return acQeStatus;
	}
	public void setAcQeStatus(int acQeStatus) {
		this.acQeStatus = acQeStatus;
	}
	
	public String toString() {
		return "ActivityQuestion [acQuContext="+acQuContext+", activityId="+activityId+"]";
	}
}
