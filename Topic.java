package com.model;

public class Topic {

	private String topicUserId;
	private String topicName;
	public String getTopicUserId() {
		return topicUserId;
	}
	public void setTopicUserId(String topicUserId) {
		this.topicUserId = topicUserId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	@Override
	public String toString() {
		return "Topic [topicUserId=" + topicUserId + ", topicName=" + topicName + "]";
	}
	
}
