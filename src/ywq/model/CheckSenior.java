package ywq.model;

public class CheckSenior {

	String id;
	String realName;	//��ʵ����
	String provinceName;	//ʡ����
	String universityName;	//��ѧ����
	String majorName;	//רҵ����
	int syear;	//��ѧ��� 
	String picSrc;	//���ͼƬ·��


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
	
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	
	public int getSyear() {
		return syear;
	}
	public void setSyear(int syear) {
		this.syear = syear;
	}
	
	public String getPicSrc() {
		return picSrc;
	}
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}
}
