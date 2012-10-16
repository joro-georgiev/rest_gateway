package com.electrical_mind.rest.app.config;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider implements Provider<EntityManager> {

	private EntityManagerFactory factory;
	
	
	public EntityManagerProvider() {
		
		factory = Persistence.createEntityManagerFactory( "sample_rest_app" );
		
	}

	@Override
	public EntityManager get() {
		return factory.createEntityManager();
	}

}
