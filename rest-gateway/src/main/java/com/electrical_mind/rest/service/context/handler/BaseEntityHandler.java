package com.electrical_mind.rest.service.context.handler;

import javax.inject.Inject;

import org.apache.log4j.Logger;



public class BaseEntityHandler<T> {
	
	@Inject 
	private Logger log;
	
	private Class<T> entityClass;

	
	protected Logger log() {
		return log;
	}
	
	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
}
