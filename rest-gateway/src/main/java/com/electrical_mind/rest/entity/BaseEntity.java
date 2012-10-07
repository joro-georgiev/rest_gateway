package com.electrical_mind.rest.entity;

import java.util.Objects;


public class BaseEntity {

	private String id;

	
	@Override
	public boolean equals(Object obj) {
		return Objects.equals( this, obj );
	}

	@Override
	public int hashCode() {
		return Objects.hashCode( this );
	}
	
	@Override
	public String toString() {
		return String.format( "[%s -id: %s %s]", getClass().getSimpleName(), id, additionalToString() );
	}
	
	protected String additionalToString() {
		return "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
