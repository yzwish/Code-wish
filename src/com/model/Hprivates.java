package com.model;

public class Hprivates {

	
	private String privateUserId;
	private int showBasic;
	private int showPreference;
	private int showTopic;
	private int showQuestion;
	private int showAnswer;
	private int showFollow;
	private int showActivity;
	public String getPrivateUserId() {
		return privateUserId;
	}
	public void setPrivateUserId(String privateUserId) {
		this.privateUserId = privateUserId;
	}
	public int getShowBasic() {
		return showBasic;
	}
	public void setShowBasic(int showBasic) {
		this.showBasic = showBasic;
	}
	public int getShowPreference() {
		return showPreference;
	}
	public void setShowPreference(int showPreference) {
		this.showPreference = showPreference;
	}
	public int getShowTopic() {
		return showTopic;
	}
	public void setShowTopic(int showTopic) {
		this.showTopic = showTopic;
	}
	public int getShowQuestion() {
		return showQuestion;
	}
	public void setShowQuestion(int showQuestion) {
		this.showQuestion = showQuestion;
	}
	public int getShowAnswer() {
		return showAnswer;
	}
	public void setShowAnswer(int showAnswer) {
		this.showAnswer = showAnswer;
	}
	public int getShowFollow() {
		return showFollow;
	}
	public void setShowFollow(int showFollow) {
		this.showFollow = showFollow;
	}
	public int getShowActivity() {
		return showActivity;
	}
	public void setShowActivity(int showActivity) {
		this.showActivity = showActivity;
	}
	@Override
	public String toString() {
		return "Hprivates [privateUserId=" + privateUserId + ", showBasic=" + showBasic + ", showPreference="
				+ showPreference + ", showTopic=" + showTopic + ", showQuestion=" + showQuestion + ", showAnswer="
				+ showAnswer + ", showFollow=" + showFollow + ", showActivity=" + showActivity + "]";
	}
	
	
}
