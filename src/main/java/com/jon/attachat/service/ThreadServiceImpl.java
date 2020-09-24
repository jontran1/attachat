package com.jon.attachat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jon.attachat.dao.ThreadDAO;
import com.jon.attachat.entity.Thread;

/**
 * Implementation of the ThreadService interface.
 * @author jonat
 *
 */
@Service
public class ThreadServiceImpl implements ThreadService {

	@Autowired
	private ThreadDAO threadDAO;
	
	@Transactional
	@Override
	public List<Thread> getThreads() {
		return threadDAO.getThreads();
	}

	@Transactional
	@Override
	public List<Thread> getSubThreads(String subName) {
		return threadDAO.getSubThreads(subName);
	}

	@Transactional
	@Override
	public Thread getThread(int threadId) {
		return threadDAO.getThread(threadId);
	}

	@Transactional
	@Override
	public void saveThread(Thread thread) {
		threadDAO.saveThread(thread);
	}

	@Transactional
	@Override
	public void deleteThread(Thread thread) {
		// TODO Auto-generated method stub

	}
	
	@Transactional
	@Override
	public void updateThread(Thread thread) {
		threadDAO.updateThread(thread);
	}

	@Transactional
	@Override
	public void saveOrUpdateThread(Thread thread) {
		threadDAO.saveOrUpdateThread(thread);
	}

	@Transactional
	@Override
	public List<Thread> searchThread(String thread, String subName) {
		return threadDAO.searchThread(thread, subName);
	}

}
