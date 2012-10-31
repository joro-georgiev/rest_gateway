package com.electrical_mind.rest.service.context.handler;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;

import com.electrical_mind.rest.service.aspect.PersistenceService;
import com.electrical_mind.rest.service.context.annotation.Transactional;


public class JPAEntityListHandler<E> extends EntityListHandler<E> implements PersistenceService {

	@Inject
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Override
	@GET
	@Transactional
	public List<? extends Object> listEntities() {
		Query query = em.createQuery( em.getCriteriaBuilder().createQuery( getEntityClass() ) );
		return query.getResultList();
	}

	@Override
	@Transactional
	public Object doCreateEntity( E entityData ) {
		em.persist( entityData );
		return entityData;
	}
	
	
	@Override
	public EntityManager entityManager() {
		return em;
	}
}
