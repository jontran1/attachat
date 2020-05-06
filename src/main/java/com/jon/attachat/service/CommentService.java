package com.jon.attachat.service;

import java.util.List;

import com.jon.attachat.entity.Comment;

public interface CommentService {
	
	public List<Comment> getComments();
	
	public List<Comment> getThreadComments(int threadId);
	
	public Comment getComment(int commentId);
	
	public void saveComment(Comment comment);
	
	public void deleteComment(Comment comment);

}
