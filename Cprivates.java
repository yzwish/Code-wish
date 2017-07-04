package com.model;

public class Cprivates {
	private String privateUserId;
	private int showBasic;
	private int showTopic;
	private int showAnswer;
	private int showFollow;
	private int showCredit;
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
	public int getShowTopic() {
		return showTopic;
	}
	public void setShowTopic(int showTopic) {
		this.showTopic = showTopic;
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
	public int getShowCredit() {
		return showCredit;
	}
	public void setShowCredit(int showCredit) {
		this.showCredit = showCredit;
	}
	@Override
	public String toString() {
		return "Cprivates [privateUserId=" + privateUserId + ", showBasic=" + showBasic + ", showTopic=" + showTopic
				+ ", showAnswer=" + showAnswer + ", showFollow=" + showFollow + ", showCredit=" + showCredit + "]";
	}
	
	
	
	
}
