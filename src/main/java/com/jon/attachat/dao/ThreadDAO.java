package com.jon.attachat.dao;

import com.jon.attachat.entity.Thread;
import java.util.List;

public interface ThreadDAO {
	
	public List<Thread> getThreads();
	
	public List<Thread> getSubThreads(String subName);
	
	public Thread getThread(int threadId);
	
	public void saveThread(Thread thread);
	
	public void deleteThread(Thread thread);

}
