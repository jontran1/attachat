package com.jon.attachat.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="user_name")
	private String userName;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
	/*
	 * A one to many relationship. mappedBy is able to map the correct user.
	 * Because it uses the Authoritie class and mapps the user to the memeber
	 * field user in Authoritie class.
	 */
	private List<Authoritie> authorities;

	
	public User() {
		
	}

	public User(String userName, boolean enabled) {
		this.userName = userName;
		this.enabled = enabled;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Authoritie> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authoritie> authorities) {
		this.authorities = authorities;
	}
	
	
}
