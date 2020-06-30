package com.jon.attachat.dao;

import com.jon.attachat.entity.Thread;
import java.util.List;

/**
 * ThreadDAO interface contains all methods for thread related
 * database operations.
 * @author jonat
 *
 */
public interface ThreadDAO {
	
	public List<Thread> getThreads();
	
	public List<Thread> getSubThreads(String subName);
	
	public Thread getThread(int threadId);
	
	public void saveThread(Thread thread);
	
	public void updateThread(Thread thread);
	
	public void deleteThread(Thread thread);
	
	public void saveOrUpdateThread(Thread thread);

}
