package com.attachat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Authorite")
public class Authoritie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="authoritie_id")
	private int authoritieId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="authority")
	private String authority;

	public Authoritie(String userName, String authority) {
		this.userName = userName;
		this.authority = authority;
	}

	public int getAuthoritieId() {
		return authoritieId;
	}

	public void setAuthoritieId(int authoritieId) {
		this.authoritieId = authoritieId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
