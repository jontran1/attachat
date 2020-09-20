package com.jon.attachat.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jon.attachat.entity.Authoritie;
import com.jon.attachat.entity.CrmUser;
import com.jon.attachat.entity.User;

/**
 * User service for all user related database operations.
 * @author jonat
 *
 */
public interface UserService extends UserDetailsService {
	
	public List<User> getUsers();
	
	public User getUser(String userName);
	
	public void saveUser(User user);
	
	public void deleteUser(User user);
	
	public void saveAuthoritie(Authoritie authoritie);
	
	public void deleteAuthoritie(Authoritie authoritie);
	
    User findByUserName(String theUserName);

    void save(CrmUser crmUser);
}
