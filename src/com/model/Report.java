package com.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Report {

	private String reportedId;//被举报者ID
	private int reportId;	//举报ID
	private int reportType; //举报类型
	private String reportReason;	//举报理由
	private Timestamp reportTime;		//举报时间
	private String reporterId;			//举报者ID
	
	public String getReportedId() {
		return reportedId;
	}
	public void setReportedId(String reportedId) {
		this.reportedId = reportedId;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public int getReportType() {
		return reportType;
	}
	public void setReportType(int reportType) {
		this.reportType = reportType;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public Timestamp getReportTime() {
		return reportTime;
	}
	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}
	public String getReporterId() {
		return reporterId;
	}
	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}
	
	
}
