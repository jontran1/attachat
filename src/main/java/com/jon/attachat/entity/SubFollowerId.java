package com.jon.attachat.entity;

import java.io.Serializable;

/**
 * Primary key for the sub_follower. 
 * @author jonat
 *
 */
public class SubFollowerId implements Serializable {
	
	private User user;
	
	private Sub sub;

	public SubFollowerId() {
	}

	public SubFollowerId(User user, Sub sub) {
		this.user = user;
		this.sub = sub;
	}
	
	

}
