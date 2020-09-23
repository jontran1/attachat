package com.jon.attachat.dao;

import java.util.LinkedList;
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

/**
 * Implementation of SubDAO interface. 
 * @author jonat
 *
 */
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
	public void decreaseSubPopulationCount(Sub sub) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		sub.setNumberOfFollowers(sub.getNumberOfFollowers() - 1);
		
		currentSession.saveOrUpdate(sub);		
	}

	@Override
	public boolean isFollower(SubFollowerId subFollowerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		SubFollower subFollower = currentSession.get(SubFollower.class, subFollowerId);

		return subFollower != null;
	}

	@Override
	public void removeFollower(SubFollower subFollower) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(subFollower);
	}

	@Override
	public SubFollower getSubFollower(SubFollowerId id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		SubFollower subFollower = currentSession.get(SubFollower.class, id);
		
		return subFollower;
	}

	@Override
	public List<SubFollower> getSubsFollowByUser(String userName) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<SubFollower> query = currentSession
				.createQuery("from SubFollower where user_name=:userName");
		query.setParameter("userName", userName);
		
		List<SubFollower> subFollowers = query.getResultList();
		
		return subFollowers;
	}

	@Override
	public List<Sub> getSubsByUser(List<SubFollower> subFollowers) {
		List<Sub> subs = new LinkedList<Sub>();
		for(SubFollower subFollower : subFollowers) {
			subs.add(subFollower.getSub());
		}
		return subs;
	}

	@Override
	public List<Sub> getSubsByUser(String userName) {
		
		List<SubFollower> subFollowers = getSubsFollowByUser(userName);
		List<Sub> subs = getSubsByUser(subFollowers);
		
		return subs;
	}

	@Override
	public List<Sub> searchSub(String sub) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Sub> query = null;
		
		if(sub != null && sub.trim().length() > 0 ) {
			
			query = currentSession.createQuery("from Sub where lower(sub_name) like :sub", Sub.class);
			query.setParameter("sub", "%" + sub.toLowerCase() + "%");
			
		}else {
			query = currentSession.createNamedQuery("from Sub", Sub.class);
		}
		
		List<Sub> subs = query.getResultList();
		
		return subs;
	}

}
