package com.jon.attachat.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Thread object relating to the thread table.
 * @author jonat
 *
 */
@Entity
@Table(name="thread")
public class Thread {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="thread_id")
	private int threadId;
	
    @Column(name="date_time", columnDefinition = "TIMESTAMP", nullable = false)
	private LocalDateTime localDateTime;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="sub_name")
	private String subName;
	
	@Column(name="thread_title")
	private String threadTitle;
	
	@Column(name="thread_content")
	private String threadContent;

	public Thread() {
		this.localDateTime = LocalDateTime.now();
	}

	public Thread(String userName, String subName, String threadTitle, String threadContent) {
		this.userName = userName;
		this.subName = subName;
		this.threadTitle = threadTitle;
		this.threadContent = threadContent;
	}
	
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
	
	public int getThreadId() {
		return threadId;
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

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	@Override
	public String toString() {
		return "Thread [threadId=" + threadId + ", userName=" + userName + ", subName=" + subName + ", threadTitle="
				+ threadTitle + ", threadContent=" + threadContent + "]";
	}
	
}
