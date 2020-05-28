package com.jon.attachat.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.jon.attachat.entity.Comment;
import com.jon.attachat.entity.Sub;
import com.jon.attachat.entity.SubFollower;
import com.jon.attachat.entity.SubFollowerId;

@Repository
public class SubDAOImpl implements SubDAO {
	
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Sub> getSubs() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Sub> query =
				currentSession.createQuery("from Sub", Sub.class);
		
		List<Sub> subs = query.getResultList();
		
		return subs;
	}

	@Override
	public Sub getSub(String subName){
		// Get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Sub sub = currentSession.get(Sub.class, subName);
		
		return sub;
	}

	@Override
	public void saveSub(Sub sub) {
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(sub);
	}

	@Override
	public void deleteSub(Sub sub) {
		// Get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.delete(sub);
	}

	@Override
	public void addFollower(SubFollower subFollower) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(subFollower);
		
		this.increaseSubPopulationCount(subFollower.getSub());
		
	}

	@Override
	public void increaseSubPopulationCount(Sub sub) {
		Session currentSession = sessionFactory.getCurrentSession();
				
		sub.setNumberOfFollowers(sub.getNumberOfFollowers() + 1);
		
		currentSession.saveOrUpdate(sub);
	}

	@Override
	public boolean isFollower(SubFollowerId subFollowerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		SubFollower subFollower = currentSession.get(SubFollower.class, subFollowerId);

		return subFollower != null;
	}

}
