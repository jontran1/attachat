package com.jon.attachat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jon.attachat.dao.AuthoritieDAO;
import com.jon.attachat.dao.UserDAO;
import com.jon.attachat.entity.Authoritie;
import com.jon.attachat.entity.User;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AuthoritieDAO authoritieDAO; 

	@Transactional
	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Transactional
	@Override
	public User getUser(String userName) {
		return userDAO.getUser(userName);
	}

	@Transactional
	@Override
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

	@Transactional
	@Override
	public void deleteUser(User user) {
		userDAO.deleteUser(user);
	}
	
	@Transactional
	@Override
	public void saveAuthoritie(Authoritie authoritie) {
		authoritieDAO.saveAuthoritie(authoritie);
	}

	@Transactional
	@Override
	public void deleteAuthoritie(Authoritie authoritie) {
		authoritieDAO.deleteAuthoritie(authoritie);
	}

}
