package com.model;

import java.sql.Timestamp;

public class Answer {
	
	private int answerId;	//回答ID
	private int answerApprovalNum;	//回答点赞数
	private String answerContent;	//回答内容
	private Timestamp answerTime;		//回答时间
	private String answerUserId;		//回答者ID
	
	//
	private int questionId;		//所属问题ID
	
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getAnswerApprovalNum() {
		return answerApprovalNum;
	}
	public void setAnswerApprovalNum(int answerApprovalNum) {
		this.answerApprovalNum = answerApprovalNum;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public Timestamp getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(Timestamp answerTime) {
		this.answerTime = answerTime;
	}
	public String getAnswerUserId() {
		return answerUserId;
	}
	public void setAnswerUserId(String answerUserId) {
		this.answerUserId = answerUserId;
	}
	
	
}
