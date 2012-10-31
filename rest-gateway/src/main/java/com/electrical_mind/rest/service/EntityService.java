package com.electrical_mind.rest.service;

import javax.inject.Inject;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.electrical_mind.rest.service.context.RestServiceContext;
import com.electrical_mind.rest.service.context.handler.EntityHandler;
import com.electrical_mind.rest.service.context.handler.EntityListHandler;
import com.sun.jersey.api.core.ResourceContext;


@Path("/")
public class EntityService {

	@Context
	private ResourceContext resourceContext;
	
	@Inject
	private RestServiceContext serviceContext;
	
	@SuppressWarnings("unchecked")
	@Path("/{entityType}")
	public EntityListHandler<?> getEntities( 
			@PathParam("entityType") String entityType
		) throws UnhandledEntityTypeException {
		
		Class<? extends EntityListHandler<?>> listHandlerClass = serviceContext.getEntityRegistry().getListHandler(entityType);
		
		if ( listHandlerClass == null ) {
			throw new UnhandledEntityTypeException();
		}
		
		EntityListHandler<Object> handler = listHandler( listHandlerClass );
		Class<Object> entityClass = (Class<Object>) entityClass(entityType);
		handler.setEntityClass( entityClass );
		return handler;
	}
	
	@SuppressWarnings("unchecked")
	@Path("/{entityType}/{id}")
	public EntityHandler<?> getEntity( 
			@PathParam("entityType") String entityType, 
			@PathParam("id") final String id 
		) throws UnhandledEntityTypeException {
		
		Class<? extends EntityHandler<?>> entityHandler = serviceContext.getEntityRegistry().getHandler(entityType);
		
		if ( entityHandler == null ) {
			throw new UnhandledEntityTypeException();
		}
		
		EntityHandler<Object> handler = entityHandler( entityHandler );
		Class<Object> entityClass = (Class<Object>) entityClass(entityType);
		handler.setEntityClass( entityClass );
		handler.setId(id);
		return handler;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> EntityListHandler<T> listHandler( Class<? extends EntityListHandler<? extends T>> handlerClass ) {
		return (EntityListHandler<T>) resourceContext.getResource( handlerClass );
	}
	
	@SuppressWarnings("unchecked")
	protected <T> EntityHandler<T> entityHandler( Class<? extends EntityHandler<? extends T>> handlerClass ) {
		return (EntityHandler<T>) resourceContext.getResource( handlerClass );
	}
	
	protected Class<?> entityClass( String entityType ) {
		return serviceContext.getEntityRegistry().getConfig( entityType ).getEntityClass();
	}
}
