package com.jon.attachat.service;

import java.util.List;

import com.jon.attachat.entity.SubFollower;
import com.jon.attachat.entity.SubFollowerId;
import com.jon.attachat.entity.Sub;

public interface SubService {
	public List<Sub> getSubs();
	
	public Sub getSub(String subName);
	
	public void saveSub(Sub sub);
	
	public void deleteSub(Sub sub);
	
	public void addFollower(SubFollower subFollower);
	
	public boolean isFollower(SubFollowerId subFollowerId);

}
