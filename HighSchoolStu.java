package com.model;

public class HighSchoolStu extends Account{

	//���ݿ��Ӧ������
	private String id;
	private int age;
	private String hId;
	private int safetyProblem;
	private String safetyAnswer;
	private String sex;
	private double balance;
	
	//�Լ���ӵ�����
	private String hsage;//���ڱ�����
	private String provinceId;
	//新增
	private String provinceName;
	private String hname;
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public String getId() {
		return id;
	}

	
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getHsage() {
		return hsage;
	}
	public void setHsage(String hsage) {
		this.hsage = hsage;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String gethId() {
		return hId;
	}
	public void sethId(String hId) {
		this.hId = hId;
	}
	public int getSafetyProblem() {
		return safetyProblem;
	}
	public void setSafetyProblem(int safetyProblem) {
		this.safetyProblem = safetyProblem;
	}
	public String getSafetyAnswer() {
		return safetyAnswer;
	}
	public void setSafetyAnswer(String safetyAnswer) {
		this.safetyAnswer = safetyAnswer;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	@Override
	public String toString() {
		return "HighSchoolStu [id=" + id + ", age=" + age + ", hId=" + hId + ", safetyProblem=" + safetyProblem
				+ ", safetyAnswer=" + safetyAnswer + ", sex=" + sex + ", balance=" + balance + ", hsage=" + hsage
				+ ", provinceId=" + provinceId + ", provinceName=" + provinceName + ", hname=" + hname + "]";
	}
	
	
	
	
}
