package com.electrical_mind.rest.app.config;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.electrical_mind.rest.app.JPAHandlerConfig;
import com.electrical_mind.rest.app.entity.Rule;
import com.electrical_mind.rest.app.entity.User;
import com.electrical_mind.rest.app.handler.UserHandler;
import com.electrical_mind.rest.app.handler.UserListHandler;
import com.electrical_mind.rest.service.context.EntityHandlerConfig;
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
		
		binder.bind( Logger.class ).toInstance( Logger.getRootLogger() );
		binder.bindListener( Matchers.any(), new LoggerTypeListener() );
		 
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
			new EntityHandlerConfig( "users", User.class ).listHandler( UserListHandler.class ).entityHandler( UserHandler.class ) 
		);
		registry.addHandler( JPAHandlerConfig.defaultConfig( "rules", Rule.class ) );
		return registry;
	}

}
