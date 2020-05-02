package com.jon.attachat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sub")
public class Sub {

	@Id
	@Column(name="sub_name")
	private String subName;

	@OneToOne()
	@JoinColumn(name="creator")
	private User creator;

	public Sub(String subName, User creator) {
		this.subName = subName;
		this.creator = creator;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
	
}
