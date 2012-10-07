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
	
	@Path("/{entityType}")
	public EntityListHandler<?> getEntities( 
			@PathParam("entityType") String entityType
		) throws UnhandledEntityTypeException {
		
		Class<? extends EntityListHandler<?>> listHandler = serviceContext.getEntityRegistry().getListHandler(entityType);
		
		if ( listHandler == null ) {
			throw new UnhandledEntityTypeException();
		}
		
		return resourceContext.getResource( listHandler );
	}
	
	@Path("/{entityType}/{id}")
	public EntityHandler<?> getEntity( 
			@PathParam("entityType") String entityType, 
			@PathParam("id") final String id 
		) throws UnhandledEntityTypeException {
		
		Class<? extends EntityHandler<?>> entityHandler = serviceContext.getEntityRegistry().getHandler(entityType);
		
		if ( entityHandler == null ) {
			throw new UnhandledEntityTypeException();
		}
		
		EntityHandler<?> handler = resourceContext.getResource( entityHandler );
		handler.setId(id);
		return handler;
	}
	
}
