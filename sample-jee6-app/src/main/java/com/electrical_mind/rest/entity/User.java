package com.electrical_mind.rest.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table( name="sample_rest_app_user" )
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;
	

	private String name;
	
	private String email;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
