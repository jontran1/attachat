package com.jon.attachat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jon.attachat.dao.CommentDAO;
import com.jon.attachat.entity.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Transactional
	@Override
	public List<Comment> getComments() {
		return commentDAO.getComments();
	}

	@Transactional
	@Override
	public List<Comment> getThreadComments(int threadId) {
		return commentDAO.getThreadComments(threadId);
	}

	@Transactional
	@Override
	public Comment getComment(int commentId) {
		return commentDAO.getComment(commentId);
	}

	@Transactional
	@Override
	public void saveComment(Comment comment) {
		commentDAO.saveComment(comment);

	}

	@Transactional
	@Override
	public void deleteComment(Comment comment) {
		// TODO Auto-generated method stub

	}
	
	@Transactional
	@Override
	public void updateCommentContent(Comment comment, String content) {
		commentDAO.updateCommentContent(comment, content);
	}

	@Transactional
	@Override
	public void updateComment(Comment comment) {
		commentDAO.updateComment(comment);
	}
	
	@Transactional
	@Override
	public List<Comment> getUserComment(String userName) {
		return commentDAO.getUserComment(userName);
	}

	@Transactional
	@Override
	public void saveOrUpdate(Comment comment) {
		commentDAO.saveOrUpdate(comment);
	}

}
