package com.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Activity implements Serializable {

	private static final long serialVersionUID = -8270777111153282039L;

	//数据表
	private int activityId;
	private String title;
	private String info;
	private String id;
	private String startTime;
	private String duration;
	private int leastNumOfEnroll;
	private int activityStatus;
	private int numOfEnroll;
	
	//新增
	private String name; //举办人姓名
	
	

	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getLeastNumOfEnroll() {
		return leastNumOfEnroll;
	}
	public void setLeastNumOfEnroll(int leastNumOfEnroll) {
		this.leastNumOfEnroll = leastNumOfEnroll;
	}
	public int getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(int activityStatus) {
		this.activityStatus = activityStatus;
	}
	public int getNumOfEnroll() {
		return numOfEnroll;
	}
	public void setNumOfEnroll(int numOfEnroll) {
		this.numOfEnroll = numOfEnroll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String toString() {
		return "Activity [title=" + title + ", info=" + info + ", startTime=" + startTime + ","
				+ " duration=" + duration + ", leastNumOfEnroll=" + leastNumOfEnroll +"]";
	}
}
