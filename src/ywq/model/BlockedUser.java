package ywq.model;

public class BlockedUser {
	String privateUserId;	//用户id
	String closureReason;	//封禁理由
	int closureDay;	//封禁天数
	String closureTime;	//封禁时间
	public String getPrivateUserId() {
		return privateUserId;
	}
	public void setPrivateUserId(String privateUserId) {
		this.privateUserId = privateUserId;
	}
	public String getClosureReason() {
		return closureReason;
	}
	public void setClosureReason(String closureReason) {
		this.closureReason = closureReason;
	}
	public int getClosureDay() {
		return closureDay;
	}
	public void setClosureDay(int closureDay) {
		this.closureDay = closureDay;
	}
	public String getClosureTime() {
		return closureTime;
	}
	public void setClosureTime(String closureTime) {
		this.closureTime = closureTime;
	}
	

}
