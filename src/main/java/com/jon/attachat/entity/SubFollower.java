package com.jon.attachat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sub_follower")
public class SubFollower {
	
	@Id
	@Column(name="user_name")
	private String userName;
	
	@Id
	@Column(name="sub_name")
	private String subName;

	public SubFollower(String userName, String subName) {
		this.userName = userName;
		this.subName = subName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	
	
}
