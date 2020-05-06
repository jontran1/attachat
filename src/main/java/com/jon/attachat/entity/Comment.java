package com.jon.attachat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="comment_id")
	private int commentId;
	
	@Column(name="thread_id")
	private int threadId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="content")
	private String content;
	
	@Column(name="parent_id")
	private Integer parentId;

	public Comment() {
	}

	public Comment(int threadId, String userName, String content, int parentId) {
		this.threadId = threadId;
		this.userName = userName;
		this.content = content;
		this.parentId = parentId;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getCommentId() {
		return commentId;
	}
	
	

}
