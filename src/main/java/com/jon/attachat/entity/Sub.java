package com.jon.attachat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sub")
public class Sub {

	@Id
	@Column(name="sub_name")
	private String subName;

	@Column(name="creator")
	private String creator;

	public Sub(String subName, String creator) {
		this.subName = subName;
		this.creator = creator;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
	
}
