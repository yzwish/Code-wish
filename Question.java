package com.model;

public class Question {

	private String userId;
	private String questionTitle;
	private String quesCreateTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuesCreateTime() {
		return quesCreateTime;
	}
	public void setQuesCreateTime(String quesCreateTime) {
		this.quesCreateTime = quesCreateTime;
	}
	@Override
	public String toString() {
		return "Question [userId=" + userId + ", questionTitle=" + questionTitle + ", quesCreateTime=" + quesCreateTime
				+ "]";
	}
	
}
