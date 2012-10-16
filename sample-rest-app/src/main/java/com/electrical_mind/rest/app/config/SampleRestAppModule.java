package com.electrical_mind.rest.app.config;

import javax.persistence.EntityManager;

import com.electrical_mind.rest.app.handler.UserHandler;
import com.electrical_mind.rest.app.handler.UserListHandler;
import com.electrical_mind.rest.service.aspect.TransactionalAspect;
import com.electrical_mind.rest.service.context.EntityRegistry;
import com.electrical_mind.rest.service.context.RestServiceContext;
import com.electrical_mind.rest.service.context.annotation.Transactional;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;

public class SampleRestAppModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind( RestServiceContext.class ).toInstance( restServiceContext() );
		binder.bind( EntityManager.class ).toProvider( EntityManagerProvider.class );
		
		binder.bindInterceptor( Matchers.any(), Matchers.annotatedWith( Transactional.class), new TransactionalAspect() );
	}

	private RestServiceContext restServiceContext() {
		RestServiceContext context = new RestServiceContext();
		context.setEntityRegistry( entityRegistry() );
		return context;
	}

	private EntityRegistry entityRegistry() {
		EntityRegistry registry = new EntityRegistry();
		registry.addHandlers( "users", UserListHandler.class, UserHandler.class );
		return registry;
	}

}
