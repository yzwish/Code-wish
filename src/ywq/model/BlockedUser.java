package ywq.model;

public class BlockedUser {
	String privateUserId;	//�û�id
	String closureReason;	//�������
	int closureDay;	//�������
	String closureTime;	//���ʱ��
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
