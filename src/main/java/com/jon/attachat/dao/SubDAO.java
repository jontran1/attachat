package com.jon.attachat.dao;

import com.jon.attachat.entity.Sub;
import com.jon.attachat.entity.SubFollower;
import com.jon.attachat.entity.SubFollowerId;

import java.util.List;

/**
 * SubDAO interface contains all methods for sub related
 * database operations.
 * @author jonat
 *
 */
public interface SubDAO {
	
	public List<Sub> getSubs();
	
	public List<Sub> getSubsByUser(List<SubFollower> subFollowers);
	
	public Sub getSub(String subName);
	
	public void saveSub(Sub sub);
	
	public void deleteSub(Sub sub);
	
	public void addFollower(SubFollower subFollower);
	
	public void removeFollower(SubFollower subFollower);
	
	public boolean isFollower(SubFollowerId subFollowerId);
	
	public void increaseSubPopulationCount(Sub sub);
	
	public void decreaseSubPopulationCount(Sub sub);
	
	public SubFollower getSubFollower(SubFollowerId id);
	
	public List<SubFollower> getSubsFollowByUser(String userName);
}
