package com.jon.attachat.service;

import java.util.List;

import com.jon.attachat.entity.SubFollower;
import com.jon.attachat.entity.SubFollowerId;
import com.jon.attachat.entity.Sub;

/**
 * Sub service for all sub related database operations.
 * @author jonat
 *
 */
public interface SubService {
	public List<Sub> getSubs();
	
	public List<Sub> getSubsByUser(List<SubFollower> subFollowers);
	
	public List<Sub> getSubsByUser(String userName);
	
	public Sub getSub(String subName);
	
	public void saveSub(Sub sub);
	
	public void deleteSub(Sub sub);
	
	public void addFollower(SubFollower subFollower);
	
	public void removeFollower(SubFollower subFollower);
	
	public boolean isFollower(SubFollowerId subFollowerId);
	
	public SubFollower getSubFollower(SubFollowerId id);
	
	public void decreaseSubPopulationCount(Sub sub);
	
	public List<SubFollower> getSubsFollowByUser(String userName);
}
