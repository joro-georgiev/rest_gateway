package com.electrical_mind.rest.service.context.handler;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import com.electrical_mind.rest.service.aspect.PersistenceService;
import com.electrical_mind.rest.service.context.annotation.Transactional;


public class JPAEntityHandler<E, D> extends EntityHandler<D> implements PersistenceService {

	@Inject
	private EntityManager em;
	
	private Class<? extends E> entityClass;
	
	@Override
	@GET
	@Transactional
	public E getEntity() {
		return em.find( entityClass, getId() );
	}
	
	@Override
	@POST
	@Transactional
	public Object updateEntity( D entityData ) {
		D result = entityManager().merge( entityData );
		return result;
	}
	
	@Override
	@DELETE
	@Transactional
	public void deleteEntity() {
		E entity = getEntity();
		em.remove( entity );
	}

	@Override
	public EntityManager entityManager() {
		return em;
	}

	public Class<? extends E> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<? extends E> entityClass) {
		this.entityClass = entityClass;
	}
}
