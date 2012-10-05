package com.electrical_mind.rest.service.context.handler;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class EntityHandler {

	private String id;
	
	@GET
	public Object getEntity() {
		return id;
	}
	
	@POST
	public Object updateEntity( Object entityData ) {
		return null;
	}
	
	@DELETE
	public void deleteEntity() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
