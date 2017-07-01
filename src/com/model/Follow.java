package com.model;

import java.sql.Date;

public class Follow {
	
	private int followId;	//关注ID
	private int followedId;	//被关注ID
	private int followType;	//关注类型
	private Date followTime;//关注时间
	private int followUserId;//关注者ID
	
	public int getFollowId() {
		return followId;
	}
	public void setFollowId(int followId) {
		this.followId = followId;
	}
	public int getFollowedId() {
		return followedId;
	}
	public void setFollowedId(int followedId) {
		this.followedId = followedId;
	}
	public int getFollowType() {
		return followType;
	}
	public void setFollowType(int followType) {
		this.followType = followType;
	}
	public Date getFollowTime() {
		return followTime;
	}
	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}
	public int getFollowUserId() {
		return followUserId;
	}
	public void setFollowUserId(int followUserId) {
		this.followUserId = followUserId;
	}
	
	
	
}
