package ywq.model;

public class CheckTeacher {

	String id;
	String realName;	//真实姓名
	String provinceName;	//省名称
	String universityName;	//大学名称
	String resDirection;	//研究方向
	String position;	//职位
	String picSrc;	//审核图片路径
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	
	public String getResDirection() {
		return resDirection;
	}
	public void setResDirection(String resDirection) {
		this.resDirection = resDirection;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getPicSrc() {
		return picSrc;
	}
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}
}
