package com.model;

public class University {

	private String universityId;
	private String universityName;
	private int type;
	private String provinceId;
	private int rank;
	private String introduction;
	private String enrollmentPlan;
	private String faculty;
	private String comment;
	public University(){
		
	}
	public University(String universityId, String universityName) {
		super();
		this.universityId = universityId;
		this.universityName = universityName;
	}
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getEnrollmentPlan() {
		return enrollmentPlan;
	}
	public void setEnrollmentPlan(String enrollmentPlan) {
		this.enrollmentPlan = enrollmentPlan;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
