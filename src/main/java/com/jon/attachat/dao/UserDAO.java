package com.jon.attachat.dao;

import java.util.List;

import com.jon.attachat.entity.User;

/**
 * UserDAO interface contains all methods for user related
 * database operations.
 * @author jonat
 *
 */
public interface UserDAO {
	
	public List<User> getUsers();
	
	public void saveUser(User user);
	
	public User getUser(String userName);
	
	public void deleteUser(User user);
	
    User findByUserName(String userName);
}
