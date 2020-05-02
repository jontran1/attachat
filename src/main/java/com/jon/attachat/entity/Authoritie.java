package com.jon.attachat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(AuthoritieId.class)
@Table(name="authoritie")
public class Authoritie {
	
	@Id
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

	public Authoritie() {
	}

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

	@Override
	public String toString() {
		return "Authoritie [user=" + user.getUserName() + ", authority=" + authority + "]";
	}
	
}
