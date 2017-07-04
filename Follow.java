package com.model;

public class Follow {
private String followUserId;
private int followType;
private String followedId;
private String followTime;


public String getFollowUserId() {
	return followUserId;
}
public void setFollowUserId(String followUserId) {
	this.followUserId = followUserId;
}
public int getFollowType() {
	return followType;
}
public void setFollowType(int followType) {
	this.followType = followType;
}
public String getFollowedId() {
	return followedId;
}
public void setFollowedId(String followedId) {
	this.followedId = followedId;
}
public String getFollowTime() {
	return followTime;
}
public void setFollowTime(String followTime) {
	this.followTime = followTime;
}
@Override
public String toString() {
	return "Follow [followUserId=" + followUserId + ", followType=" + followType + ", followedId=" + followedId
			+ ", followTime=" + followTime + "]";
}

}
