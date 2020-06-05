package com.jon.attachat.dao;

import com.jon.attachat.entity.Sub;
import com.jon.attachat.entity.SubFollower;
import com.jon.attachat.entity.SubFollowerId;

import java.util.List;

public interface SubDAO {
	
	public List<Sub> getSubs();
	
	public Sub getSub(String subName);
	
	public void saveSub(Sub sub);
	
	public void deleteSub(Sub sub);
	
	public void addFollower(SubFollower subFollower);
	
	public void removeFollower(SubFollower subFollower);
	
	public boolean isFollower(SubFollowerId subFollowerId);
	
	public void increaseSubPopulationCount(Sub sub);
	
	public void decreaseSubPopulationCount(Sub sub);
	
	public SubFollower getSubFollower(SubFollowerId id);
}
