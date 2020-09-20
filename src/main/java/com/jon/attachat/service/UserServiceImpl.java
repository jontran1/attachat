package com.jon.attachat.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jon.attachat.dao.AuthoritieDAO;
import com.jon.attachat.dao.UserDAO;
import com.jon.attachat.entity.Authoritie;
import com.jon.attachat.entity.CrmUser;
import com.jon.attachat.entity.User;

/**
 * Implementation of UserService interface.
 * @author jonat
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AuthoritieDAO authoritieDAO; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

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

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDAO.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getAuthorities()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authoritie> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getAuthority())).collect(Collectors.toList());
	}

	
	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setEnabled(true);
		Authoritie auth = new Authoritie(user ,"USER");
		user.addAuthoritie(auth);

		 // save user in the database
		userDAO.saveUser(user);
		authoritieDAO.saveAuthoritie(auth);
	}
	
	@Override
	@Transactional
	public User findByUserName(String theUserName) {
		return userDAO.findByUserName(theUserName);
	}

}
