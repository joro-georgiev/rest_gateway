package com.electrical_mind.rest.service.context.handler;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;


public class JPAEntityHandler<E> extends EntityHandler {

	@Inject
	private EntityManager em;
	
	@Override
	@GET
	public E getEntity() {
		return em.find( entityClass(), getId() );
	}
	
	@Override
	@DELETE
	public void deleteEntity() {
		E entity = getEntity();
		em.remove( entity );
	}
	
	@Override
	@POST
	public Object updateEntity(Map<String, String> entityData) {
		E entity = getEntity();
		updateData( entity, entityData );
		return entity;
	}
	
	
	protected void updateData(E entity, Map<String, String> entityData) {
		//TODO implement me
	}

	@SuppressWarnings("unchecked")
	private Class<? extends E> entityClass() {
		ParameterizedType pType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<? extends E>) pType.getActualTypeArguments()[0];
	}
}
