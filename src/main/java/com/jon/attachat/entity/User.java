package com.jon.attachat.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user", cascade= {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
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
	
	public User(String userName, String password, boolean enabled) {
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void addAuthoritie(Authoritie authoritie) {
		if(authorities == null) authorities = new ArrayList();
		authorities.add(authoritie);
	}
	
	public void deleteAuthoritie(Authoritie authoritie) {
		if(authorities == null) return;
		authorities.remove(authoritie);
	}

	public List<Authoritie> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authoritie> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", enabled=" + enabled + ", authorities=" + authorities + "]";
	}
	
	
}
