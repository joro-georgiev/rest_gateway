package com.electrical_mind.rest.app.config;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import com.electrical_mind.rest.app.JPAHandlerConfig;
import com.electrical_mind.rest.entity.User;
import com.electrical_mind.rest.service.context.EntityRegistry;
import com.electrical_mind.rest.service.context.RestServiceContext;


public class RestContextProvider {

	@Produces
	public RestServiceContext getRestServiceContext( InjectionPoint injectionPoint ) {
		RestServiceContext context = new RestServiceContext();
		context.setEntityRegistry( entityRegistry() );
		return context;
	}
	
	private EntityRegistry entityRegistry() {
		EntityRegistry registry = new EntityRegistry();
		registry.addHandler( JPAHandlerConfig.defaultConfig( "users", User.class ) );
		return registry;
	}

}
