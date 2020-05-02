package com.jon.attachat.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jon.attachat.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<User> getUsers() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> query = 
				currentSession.createQuery("from User",
											User.class);
		
		List<User> users = query.getResultList();
		
		return users;
	}

	@Transactional
	@Override
	public void saveUser(User user) {
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(user);
		
	}

	@Transactional
	@Override
	public User getUser(String userName) {
		// Get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Now retrieve/read from database using primary key.
		User user = currentSession.get(User.class, userName);
		
		return user;
	}

	@Transactional
	@Override
	public void deleteUser(User user) {
		// Get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.delete(user);
	}

}
