package com.model;

public class Major {

	private String majorId;
	private String majorName;
	private String majorDiscription;
	private String coreCourses;
	private String employment;
	private String rank;
	private int kindId ;
	
    public Major(){
		
	}
	public Major(String majorId, String majorName) {
		super();
		this.majorId = majorId;
		this.majorName = majorName;
	}
	
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getMajorDiscription() {
		return majorDiscription;
	}
	public void setMajorDiscription(String majorDiscription) {
		this.majorDiscription = majorDiscription;
	}
	public String getCoreCourses() {
		return coreCourses;
	}
	public void setCoreCourses(String coreCourses) {
		this.coreCourses = coreCourses;
	}
	public String getEmployment() {
		return employment;
	}
	public void setEmployment(String employment) {
		this.employment = employment;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	@Override
	public String toString() {
		return "Major [majorId=" + majorId + ", majorName=" + majorName + ", majorDiscription=" + majorDiscription
				+ ", coreCourses=" + coreCourses + ", employment=" + employment + ", rank=" + rank + ", kindId="
				+ kindId + "]";
	}
	
}
