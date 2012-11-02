package com.electrical_mind.rest.jee6.service;

import javax.ws.rs.Path;

import com.electrical_mind.rest.app.config.SampleRestApp;
import com.electrical_mind.rest.service.EntityService;
import com.electrical_mind.rest.service.context.handler.EntityHandler;
import com.electrical_mind.rest.service.context.handler.EntityListHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;


@Path("/")
public class JEE6EntityService extends EntityService {

	private Injector injector =  Guice.createInjector( SampleRestApp.Injections.getModule() );
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T> EntityListHandler<T> listHandler( Class<? extends EntityListHandler<? extends T>> handlerClass ) {
		return (EntityListHandler<T>) injector.getInstance( handlerClass );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T> EntityHandler<T> entityHandler( Class<? extends EntityHandler<? extends T>> handlerClass ) {
		return (EntityHandler<T>) injector.getInstance( handlerClass );
	}
}
