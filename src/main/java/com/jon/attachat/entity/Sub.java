package com.jon.attachat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Sub object, relating to the sub table.
 * @author jonat
 *
 */
@Entity
@Table(name="sub")
public class Sub {

	@Id
	@Column(name="sub_name")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String subName;

	@Column(name="creator")
	private String creator;
	
	@Column(name="number_of_followers")
	private int numberOfFollowers;
	
	public Sub() {
		
	}

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

	public int getNumberOfFollowers() {
		return numberOfFollowers;
	}

	public void setNumberOfFollowers(int numberOfFollowers) {
		this.numberOfFollowers = numberOfFollowers;
	}

	@Override
	public String toString() {
		return "Sub [subName=" + subName + ", creator=" + creator + "]";
	}
	
}
