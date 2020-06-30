package com.jon.attachat.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jon.attachat.entity.Thread;

/**
 * Implementation of ThreadDAO interface.
 * @author jonat
 *
 */
@Repository
public class ThreadDAOImpl implements ThreadDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Thread> getThreads() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Thread> query =
				currentSession.createQuery("from Thread", Thread.class);
		
		List<Thread> threads = query.getResultList();
		
		return threads;
	}
	
	@Override
	public List<Thread> getSubThreads(String subName) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Thread> query =
				currentSession.createQuery("from Thread WHERE sub_name=:subName");
		query.setParameter("subName", subName);
		
		List<Thread> threads = query.getResultList();
		
		return threads;
	}

	@Override
	public Thread getThread(int threadId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Thread thread = currentSession.get(Thread.class, threadId);
		
		return thread;
	}

	@Override
	public void saveThread(Thread thread) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(thread);
	}

	@Override
	public void deleteThread(Thread thread) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateThread(Thread thread) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.update(thread);
	}

	@Override
	public void saveOrUpdateThread(Thread thread) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(thread);		
	}

}
