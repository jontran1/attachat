package com.jon.attachat.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jon.attachat.dao.SubDAO;
import com.jon.attachat.entity.SubFollower;
import com.jon.attachat.entity.SubFollowerId;
import com.jon.attachat.entity.Sub;

@Service
public class SubServiceImpl implements SubService {

	@Autowired
	private SubDAO subDAO;
	
	@Transactional
	@Override
	public List<Sub> getSubs() {
		return subDAO.getSubs();
	}

	@Transactional
	@Override
	public Sub getSub(String subName) {
		return subDAO.getSub(subName);
	}

	@Transactional
	@Override
	public void saveSub(Sub sub) {
		subDAO.saveSub(sub);
	}

	@Transactional
	@Override
	public void deleteSub(Sub sub) {
		subDAO.deleteSub(sub);
	}

	@Transactional
	@Override
	public void addFollower(SubFollower subFollower) {
		subDAO.addFollower(subFollower);
	}

	@Transactional
	@Override
	public boolean isFollower(SubFollowerId subFollowerId) {
		return subDAO.isFollower(subFollowerId);
	}

	@Transactional
	@Override
	public void removeFollower(SubFollower subFollower) {
		subDAO.removeFollower(subFollower);		
	}

	@Override
	public SubFollower getSubFollower(SubFollowerId id) {
		return subDAO.getSubFollower(id);
	}

	@Transactional
	@Override
	public void decreaseSubPopulationCount(Sub sub) {
		subDAO.decreaseSubPopulationCount(sub);
	}

}
