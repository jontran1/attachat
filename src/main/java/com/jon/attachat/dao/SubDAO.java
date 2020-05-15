package com.jon.attachat.dao;

import com.jon.attachat.entity.Sub;
import java.util.List;

public interface SubDAO {
	
	public List<Sub> getSubs();
	
	public Sub getSub(String subName);
	
	public void saveSub(Sub sub);
	
	public void deleteSub(Sub sub);
	
	public void addFollower(Sub sub, String userName);
}
