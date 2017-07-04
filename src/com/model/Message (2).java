package com.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {
	private static final long serialVersionUID = 851443196082204738L;

	private int mId;
	private String id;
	private String context;
	private Timestamp time;
	private int isRead;
	
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	
}
