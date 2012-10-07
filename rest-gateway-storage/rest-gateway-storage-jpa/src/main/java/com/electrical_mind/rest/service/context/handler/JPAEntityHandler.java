package com.electrical_mind.rest.service.context.handler;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;


public class JPAEntityHandler<E, D> extends EntityHandler<D> {

	@Inject
	private EntityManager em;
	
	
	@Override
	@GET
	public E getEntity() {
		return em.find( entityClass(), getId() );
	}
	
	@Override
	@POST
	public Object updateEntity( D entityData ) {
		D result = em().merge( entityData );
		return result;
	}
	
	@Override
	@DELETE
	public void deleteEntity() {
		E entity = getEntity();
		em.remove( entity );
	}

	protected EntityManager em() {
		return em;
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends E> entityClass() {
		ParameterizedType pType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<? extends E>) pType.getActualTypeArguments()[0];
	}
}
