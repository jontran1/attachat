package com.jon.attachat.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jon.attachat.entity.Thread;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveThread(Thread thread) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteThread(Thread thread) {
		// TODO Auto-generated method stub

	}

}
