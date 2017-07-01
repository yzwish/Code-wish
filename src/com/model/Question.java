package com.model;

import java.sql.Date;
import java.sql.Timestamp;



public class Question {
	private int followNumber;//关注量
	private Date questionCreateTime;	//提问时间
	private String userId;					//提问者ID
	private int questionId;				//问题ID
	private String quesTitle;			//问题标题
	private int answerCount;			//问题回答数
	private String quesContent;			//问题内容
	
	//
	private Timestamp lastAnswerTime;		//最后回复时间
	private int topicId;				//所属话题ID
	
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public Timestamp getLastAnswerTime() {
		return lastAnswerTime;
	}
	public void setLastAnswerTime(Timestamp lastAnswerTime) {
		this.lastAnswerTime = lastAnswerTime;
	}
	public int getFollowNumber() {
		return followNumber;
	}
	public void setFollowNumber(int followNumber) {
		this.followNumber = followNumber;
	}
	public Date getQuestionCreateTime() {
		return questionCreateTime;
	}
	public void setQuestionCreateTime(Date questionCreateTime) {
		this.questionCreateTime = questionCreateTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuesTitle() {
		return quesTitle;
	}
	public void setQuesTitle(String quesTitle) {
		this.quesTitle = quesTitle;
	}
	public int getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	public String getQuesContent() {
		return quesContent;
	}
	public void setQuesContent(String quesContent) {
		this.quesContent = quesContent;
	}
	
	
	
}
