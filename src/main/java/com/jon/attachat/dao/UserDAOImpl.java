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
	
	@Override
	@Transactional
	public List<User> getUsers() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> query = 
				currentSession.createQuery("from User",
											User.class);
		
		List<User> users = query.getResultList();
		
		return users;
	}

}
