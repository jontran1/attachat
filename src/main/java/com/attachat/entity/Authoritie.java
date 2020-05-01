package com.attachat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Authorite")
public class Authoritie {
	
	@Id
	@Column(name="user_name")
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_name")
	/**
	 * Spring will automagically look at the user class, Generate a bean and map
	 * it to Authorite class.
	 */
	private User user;
	
	@Id
	@Column(name="authority")
	private String authority;

	public Authoritie(User userName, String authority) {
		this.user = userName;
		this.authority = authority;
	}

	public User getUserName() {
		return user;
	}

	public void setUserName(User userName) {
		this.user = userName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
