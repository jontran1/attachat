package com.jon.attachat.entity;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
import javax.persistence.Transient;

/*
 * Hibernate ORM maps the comment table in the MySQL database 
 * to the Comment object.
 */
/**
 * Comment object relating to the comment table. 
 * @author jonat
 *
 */
@Entity
@Table(name="comment")
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="comment_id")
	private int commentId;
	
	@Column(name="date_time", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime localDateTime;
	
	@Column(name="thread_id")
	private int threadId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="content")
	private String content;
	
	@Column(name="parent_id")
	private Integer parentId;
	
	@Column(name="deleted")
	private Boolean deleted;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="parentId", cascade= {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Comment> children;
	
	@Transient
	public int indent;
	
	public Comment() {
	}

	public Comment(int threadId, String userName, String content, Comment parent) {
		this.threadId = threadId;
		this.userName = userName;
		this.content = content;
		this.deleted = false;
	}
	
	public Comment(int threadId, String userName, String content, Integer parentId) {
		this.threadId = threadId;
		this.userName = userName;
		this.content = content;
		this.parentId = parentId;
		this.deleted = false;
	}
	
	public Comment(int threadId, String userName, String content) {
		this.threadId = threadId;
		this.userName = userName;
		this.content = content;
		this.parentId = null;
		this.deleted = false;
	}

	/*
	 * Getters and Setters.
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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

	public Integer getParentId() {
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

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", localDateTime=" + localDateTime + ", threadId=" + threadId
				+ ", userName=" + userName + ", content=" + content + ", parentId=" + parentId + ", deleted=" + deleted
				+ "]";
	}

	/*
	 * Indents comment's children. Performs a level traverse children
	 * and add indent by 10 each level.
	 * This will allow the view to have a nice comment and replies view
	 * for the user.
	 *  
	 */
	public void indentReplies() {
		Comment comment = this;
		int indent = 0;
		Queue<Comment> queue = new LinkedList<>();
		queue.add(comment);
		comment.indent = indent;

		while(!queue.isEmpty()) {
			Comment node = queue.remove();
			indent += 10;
			for(Comment child : node.getChildren()) {
				queue.add(child);
				child.indent = indent;
			}
			
		}
	}

}
