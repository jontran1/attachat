package com.jon.attachat.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jon.attachat.entity.Authoritie;
/**
 * Implementation of the AuthoritieDAO interface. 
 * @author jonat
 *
 */
@Repository
public class AuthoritieImpl implements AuthoritieDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveAuthoritie(Authoritie authoritie) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(authoritie);
	}

	@Override
	public void deleteAuthoritie(Authoritie authoritie) {
		// Get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.delete(authoritie);
	}

}
