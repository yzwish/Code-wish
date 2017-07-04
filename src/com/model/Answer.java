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
	private String questionUserId;//所属问题提问人ID
	private String questionTitle;//所属问题标题
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionUserId() {
		return questionUserId;
	}
	public void setQuestionUserId(String questionUserId) {
		this.questionUserId = questionUserId;
	}
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
