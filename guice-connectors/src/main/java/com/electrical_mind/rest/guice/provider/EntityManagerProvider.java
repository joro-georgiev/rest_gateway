package com.electrical_mind.rest.guice.provider;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider implements Provider<EntityManager>, com.google.inject.Provider<EntityManager> {

	private EntityManagerFactory factory;
	
	public EntityManagerProvider(String unitName) {
		factory = Persistence.createEntityManagerFactory( unitName );
	}

	
	@Override
	public EntityManager get() {
		return factory.createEntityManager();
	}

}
