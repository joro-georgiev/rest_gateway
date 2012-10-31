package com.electrical_mind.rest.service.context.handler;



public class BaseEntityHandler<T> {

	private Class<T> entityClass;

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
}
