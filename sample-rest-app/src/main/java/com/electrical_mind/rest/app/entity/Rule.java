package com.electrical_mind.rest.app.entity;

import javax.persistence.Entity;

import com.electrical_mind.rest.entity.BaseEntity;

@Entity
public class Rule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private int number;
	
	private String description;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
