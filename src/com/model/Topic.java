package com.model;

import org.springframework.web.multipart.MultipartFile;

public class Topic {

	private int followNumber;	//关注量
	private String topicBanner;	//话题banner
	private int topicId;		//话题ID
	private int topicUserId;	//话题发起人ID
	private String topicDiscription;	//话题简介
	private String topicName;			//话题名称
	private int topicState;				//话题审核状态
	private String topicAvatar;			//话题头像
	
	//
	private MultipartFile bannerPic;
	private MultipartFile avatarPic;
	

	public MultipartFile getBannerPic() {
		return bannerPic;
	}
	public void setBannerPic(MultipartFile bannerPic) {
		this.bannerPic = bannerPic;
	}
	public MultipartFile getAvatarPic() {
		return avatarPic;
	}
	public void setAvatarPic(MultipartFile avatarPic) {
		this.avatarPic = avatarPic;
	}
	public int getFollowNumber() {
		return followNumber;
	}
	public void setFollowNumber(int followNumber) {
		this.followNumber = followNumber;
	}
	public String getTopicBanner() {
		return topicBanner;
	}
	public void setTopicBanner(String topicBanner) {
		this.topicBanner = topicBanner;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public int getTopicUserId() {
		return topicUserId;
	}
	public void setTopicUserId(int topicUserId) {
		this.topicUserId = topicUserId;
	}
	public String getTopicDiscription() {
		return topicDiscription;
	}
	public void setTopicDiscription(String topicDiscription) {
		this.topicDiscription = topicDiscription;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public int getTopicState() {
		return topicState;
	}
	public void setTopicState(int topicState) {
		this.topicState = topicState;
	}
	public String getTopicAvatar() {
		return topicAvatar;
	}
	public void setTopicAvatar(String topicAvatar) {
		this.topicAvatar = topicAvatar;
	}
	
}
