package com.jon.attachat.dao;

import java.util.List;

import com.jon.attachat.entity.Comment;
/**
 * CommentDAO interface contains all methods for comment related
 * database operations.
 * @author jonat
 *
 */
public interface CommentDAO {

	public List<Comment> getComments();
	
	public List<Comment> getThreadParentComments(int threadId);
	
	public Comment getComment(int commentId);
	
	public void saveComment(Comment comment);
	
	public void saveOrUpdate(Comment comment);
	
	public void deleteComment(Comment comment);
	
	public void updateCommentContent(Comment comment, String content);
	
	public void updateComment(Comment comment);
	
	public List<Comment> getUserComment(String userName);
	
}
