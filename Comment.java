package com.model;

import java.util.Date;

public class Comment {

	
	private int commentId;
	private String commentedUserId;
	private String commentUserId;
	private String commentContent;
	private String commentTime;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentedUserId() {
		return commentedUserId;
	}
	public void setCommentedUserId(String commentedUserId) {
		this.commentedUserId = commentedUserId;
	}
	public String getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentedUserId=" + commentedUserId + ", commentUserId="
				+ commentUserId + ", commentContent=" + commentContent + ", commentTime=" + commentTime + "]";
	}
	
	
	
	
}
