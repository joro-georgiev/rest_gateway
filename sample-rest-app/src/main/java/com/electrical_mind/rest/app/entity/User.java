package com.electrical_mind.rest.app.entity;

import com.electrical_mind.rest.entity.BaseEntity;

public class User extends BaseEntity {

	private String name;
	
	private String email;

	@Override
	protected String additionalToString() {
		return String.format( "-name: %s -email: %s", name, email );
	}
	
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
