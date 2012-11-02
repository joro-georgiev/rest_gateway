package com.electrical_mind.rest.service.context.handler;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;


@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public abstract class EntityListHandler<E> extends BaseEntityHandler<E> {
	
	@GET
	public abstract List<? extends Object> listEntities();
	
	@PUT
	public final Object createEntity( String entityData ) {
		try {
			
			log().info( "Creating new entity of type: " + getEntityClass() );
			
			E entity = new ObjectMapper().readValue( entityData, getEntityClass() );
			return doCreateEntity( entity );
		} catch ( IOException e ) {
			throw new WebApplicationException( e );
		}
	}

	protected abstract Object doCreateEntity( E entity );
}

