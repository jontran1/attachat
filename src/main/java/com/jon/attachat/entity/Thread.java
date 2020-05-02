package com.jon.attachat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="thread")
public class Thread {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="thread_id")
	private int threadId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="sub_name")
	private String subName;
	
	@Column(name="thread_title")
	private String threadTitle;
	
	@Column(name="thread_content")
	private String threadContent;

	public Thread(String userName, String subName, String threadTitle, String threadContent) {
		this.userName = userName;
		this.subName = subName;
		this.threadTitle = threadTitle;
		this.threadContent = threadContent;
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

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getThreadTitle() {
		return threadTitle;
	}

	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}

	public String getThreadContent() {
		return threadContent;
	}

	public void setThreadContent(String threadContent) {
		this.threadContent = threadContent;
	}
	
	
}
