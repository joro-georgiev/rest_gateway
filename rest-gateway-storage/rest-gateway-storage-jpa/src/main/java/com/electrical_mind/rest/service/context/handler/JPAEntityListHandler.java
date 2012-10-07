package com.electrical_mind.rest.service.context.handler;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

public class JPAEntityListHandler<E> extends EntityListHandler<E> {

	@Inject
	private EntityManager em;
	
	
	@Override
	@GET
	@SuppressWarnings("unchecked")
	public List<? extends Object> listEntities() {
		return (List<? extends Object>) em.createQuery( em.getCriteriaBuilder().createQuery( entityClass() ) );
	}

	@Override
	@PUT
	public Object createEntity(E entityData) {
		em.persist( entityData );
		return entityData;
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends E> entityClass() {
		ParameterizedType pType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<? extends E>) pType.getActualTypeArguments()[0];
	}
}
