package com.jon.attachat.service;

import java.util.List;

import com.jon.attachat.entity.Authoritie;
import com.jon.attachat.entity.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public User getUser(String userName);
	
	public void saveUser(User user);
	
	public void deleteUser(User user);
	
	public void saveAuthoritie(Authoritie authoritie);
	
	public void deleteAuthoritie(Authoritie authoritie);

}
