package com.jon.attachat.service;

import java.util.List;

import com.jon.attachat.entity.Thread;

public interface ThreadService {
	
	public List<Thread> getThreads();
	
	public List<Thread> getSubThreads(String subName);
	
	public Thread getThread(int threadId);
	
	public void saveThread(Thread thread);
	
	public void updateThread(Thread thread);
	
	public void deleteThread(Thread thread);

}
