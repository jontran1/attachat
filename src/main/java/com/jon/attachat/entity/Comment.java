package com.jon.attachat.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="parentId", cascade= {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Comment> children;
	
	public Comment() {
	}

	public Comment(int threadId, String userName, String content, Comment parent) {
		this.threadId = threadId;
		this.userName = userName;
		this.content = content;
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

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public List<Comment> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", threadId=" + threadId + ", userName=" + userName + ", content="
				+ content + ", parentId=" + parentId + "]";
	}

	

}
