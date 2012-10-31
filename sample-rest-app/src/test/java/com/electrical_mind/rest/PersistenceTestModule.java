package com.electrical_mind.rest;

import javax.persistence.EntityManager;

import com.electrical_mind.rest.app.JPAHandlerConfig;
import com.electrical_mind.rest.app.config.EntityManagerProvider;
import com.electrical_mind.rest.app.config.TranscationInterceptor;
import com.electrical_mind.rest.app.entity.User;
import com.electrical_mind.rest.service.context.EntityRegistry;
import com.electrical_mind.rest.service.context.RestServiceContext;
import com.electrical_mind.rest.service.context.annotation.Transactional;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;

public class PersistenceTestModule implements Module {

	private String testEntityType;

	public PersistenceTestModule(String testEntityType) {
		this.testEntityType = testEntityType;
	}

	
	@Override
	public void configure(Binder binder) {
		binder.bind( RestServiceContext.class ).toInstance( restServiceContext() );
		binder.bind( EntityManager.class ).toProvider( EntityManagerProvider.class );
		
		binder.bindInterceptor( Matchers.any(), Matchers.annotatedWith( Transactional.class), new TranscationInterceptor() );
	}

	private RestServiceContext restServiceContext() {
		RestServiceContext context = new RestServiceContext();
		context.setEntityRegistry( entityRegistry() );
		return context;
	}

	private EntityRegistry entityRegistry() {
		EntityRegistry registry = new EntityRegistry();
		registry.addHandler( 
			JPAHandlerConfig.defaultConfig( testEntityType, User.class )
		);
		return registry;
	}

}
