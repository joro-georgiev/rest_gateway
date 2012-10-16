package com.electrical_mind.rest.entity;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
