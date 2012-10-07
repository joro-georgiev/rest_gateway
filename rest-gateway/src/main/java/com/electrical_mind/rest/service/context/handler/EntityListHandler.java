package com.electrical_mind.rest.service.context.handler;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public abstract class EntityListHandler<D> {
	
	@GET
	public abstract List<? extends Object> listEntities();
	
	@PUT
	public abstract Object createEntity( D entityData );
}

