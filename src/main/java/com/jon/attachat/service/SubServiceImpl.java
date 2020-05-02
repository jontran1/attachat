package com.jon.attachat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jon.attachat.dao.SubDAO;
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

}
