package com.model;

public class Answer {

	private String answerUserId;
	private String answerContent;
	private String answerTime;
	public String getAnswerUserId() {
		return answerUserId;
	}
	public void setAnswerUserId(String answerUserId) {
		this.answerUserId = answerUserId;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	@Override
	public String toString() {
		return "Answer [answerUserId=" + answerUserId + ", answerContent=" + answerContent + ", answerTime="
				+ answerTime + "]";
	}
	
	
}
