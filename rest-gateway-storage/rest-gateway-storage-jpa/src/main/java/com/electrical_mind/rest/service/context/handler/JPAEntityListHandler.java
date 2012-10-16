package com.electrical_mind.rest.service.context.handler;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

import com.electrical_mind.rest.service.aspect.PersistenceService;
import com.electrical_mind.rest.service.context.annotation.Transactional;


public class JPAEntityListHandler<E> extends EntityListHandler<E> implements PersistenceService {

	private Class<? extends E> entityClass;
	
	@Inject
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Override
	@GET
	@Transactional
	public List<? extends Object> listEntities() {
		Query query = em.createQuery( em.getCriteriaBuilder().createQuery( entityClass ) );
		return query.getResultList();
	}

	@Override
	@PUT
	@Transactional
	public Object createEntity(E entityData) {
		em.persist( entityData );
		return entityData;
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
