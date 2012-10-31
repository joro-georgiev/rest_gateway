package com.electrical_mind.rest.service.context.handler;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import com.electrical_mind.rest.service.aspect.PersistenceService;
import com.electrical_mind.rest.service.context.annotation.Transactional;

public class JPAEntityHandler<E> extends EntityHandler<E> implements PersistenceService {

	@Inject
	private EntityManager em;
	
	@Override
	@GET
	@Transactional
	public Object getEntity() {
		return em.find( getEntityClass(), getId() );
	}
	
	@Override
	@POST
	@Transactional
	public Object updateEntity( E entityData ) {
		Object result = entityManager().merge( entityData );
		return result;
	}
	
	@Override
	@DELETE
	@Transactional
	public void deleteEntity() {
		E entity = em.find( getEntityClass(), getId() );
		em.remove( entity );
		em.flush();
	}

	@Override
	public EntityManager entityManager() {
		return em;
	}
}

