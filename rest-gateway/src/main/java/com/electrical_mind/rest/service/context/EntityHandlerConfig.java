package com.electrical_mind.rest.service.context;

import com.electrical_mind.rest.service.context.handler.EntityHandler;
import com.electrical_mind.rest.service.context.handler.EntityListHandler;


public class EntityHandlerConfig {

	
	private String entityType;
	
	private Class<? extends EntityHandler<?>> entityHandler;
	
	private Class<? extends EntityListHandler<?>> listHandler;
	
	private Class<?> entityClass;
	
	
	public EntityHandlerConfig() {
	}

	public EntityHandlerConfig( String entityType ) {
		entityType(entityType);
	}
	
	public EntityHandlerConfig( String entityType, Class<?> entityClass ) {
		entityType(entityType);
		entityClass(entityClass);
	}
	
	public EntityHandlerConfig entityType( String entityType ) {
		this.entityType = entityType;
		return this;
	}
	
	public EntityHandlerConfig entityHandler( Class<? extends EntityHandler<?>>  entityHandler ) {
		this.entityHandler = entityHandler;
		return this;
	}
	
	public EntityHandlerConfig listHandler( Class<? extends EntityListHandler<?>> listHandler ) {
		this.listHandler = listHandler;
		return this;
	}

	public EntityHandlerConfig entityClass(Class<?> entityClass ) {
		this.entityClass = entityClass;
		return this;
	}
	
	public String getEntityType() {
		return entityType;
	}

	public Class<? extends EntityHandler<?>> getEntityHandler() {
		return entityHandler;
	}

	public Class<? extends EntityListHandler<?>> getListHandler() {
		return listHandler;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}
}
