package ywq.model;

public class Report {
	int reportID;	//举报ID
	String reporterID;	//举报者id
	String reportedID;	//被举报者id/被举报话题id
	String reportReason;	//举报理由
	String reportTime;	//举报时间
	
	String topicName;	//话题名称
	String topicUserId;	//话题发起人id
	
	public int getReportID() {
		return reportID;
	}
	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	public String getReporterID() {
		return reporterID;
	}
	public void setReporterID(String reporterID) {
		this.reporterID = reporterID;
	}
	public String getReportedID() {
		return reportedID;
	}
	public void setReportedID(String reportedID) {
		this.reportedID = reportedID;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicUserId() {
		return topicUserId;
	}
	public void setTopicUserId(String topicUserId) {
		this.topicUserId = topicUserId;
	}
	
}
