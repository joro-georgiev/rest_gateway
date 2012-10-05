package com.electrical_mind.rest.service.context.handler;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class EntityListHandler {
	
	@GET
	public List<Object> listEntities() {
		return new ArrayList<>();
	}
	
	@PUT
	public Object createEntity( Object entityData ) {
		return null;
	}
}

