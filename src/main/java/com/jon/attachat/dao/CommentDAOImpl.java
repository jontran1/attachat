package com.jon.attachat.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jon.attachat.entity.Comment;
/**
 * Implementation of the CommentDAO interface. 
 * @author jonat
 *
 */
@Repository
public class CommentDAOImpl implements CommentDAO {
	
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Comment> getComments() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Comment> query =
				currentSession.createQuery("from Comment", Comment.class);
		
		List<Comment> comments = query.getResultList();
		
		return comments;
	}
	
	@Override
	public List<Comment> getThreadParentComments(int threadId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Comment> query = 
				currentSession.createQuery("from Comment WHERE thread_id=:threadId AND parent_id = NULL");
		query.setParameter("threadId", threadId);
		
		List<Comment> comments = query.getResultList();
		
		return comments;
	}

	@Override
	public Comment getComment(int commentId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Comment comment = currentSession.get(Comment.class, commentId);
		
		return comment;

	}

	@Override
	public void saveComment(Comment comment) {
		Session currentSession = sessionFactory.getCurrentSession();
				
		currentSession.save(comment);
	}

	@Override
	public void deleteComment(Comment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCommentContent(Comment comment, String content) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		comment.setContent(content);
		
		currentSession.update(comment);
		
	}

	@Override
	public void updateComment(Comment comment) {
		Session currentSession = sessionFactory.getCurrentSession();
				
		currentSession.update(comment);		
	}

	@Override
	public List<Comment> getUserComment(String userName) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Comment> query = 
				currentSession.createQuery("from Comment WHERE user_name=:userName");
		query.setParameter("userName", userName);
		
		List<Comment> comments = query.getResultList();
		
		return comments;
		
		
	}

	/*
	 * If the comment exist in the database. The comment will be updated.
	 * If the comment doesn't exist, A new row will be added.
	 */
	@Override
	public void saveOrUpdate(Comment comment) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(comment);
	}
}
