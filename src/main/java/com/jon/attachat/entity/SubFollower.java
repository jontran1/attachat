package com.jon.attachat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SubFollower object relating to the sub_follower table.
 * @author jonat
 *
 */
@Entity
@IdClass(SubFollowerId.class)
@Table(name="sub_follower")
public class SubFollower {
	
	@Id
	@JoinColumn(name="user_name")
	@ManyToOne
	private User user;
	
	@Id
	@JoinColumn(name="sub_name")
	@ManyToOne
	private Sub sub;

	public SubFollower(User user, Sub sub) {
		this.user = user;
		this.sub = sub;
	}

	public SubFollower() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Sub getSub() {
		return sub;
	}

	public void setSub(Sub sub) {
		this.sub = sub;
	}


	
}
