package com.model;

public class Activity {

	
	private String id;
	private String title;
	private int	activityStatus;
	private String startTime;
	@Override
	public String toString() {
		return "Activity [id=" + id + ", title=" + title + ", activityStatus=" + activityStatus + ", startTime="
				+ startTime + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(int activityStatus) {
		this.activityStatus = activityStatus;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
}
