package com.electrical_mind.rest.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



public class UnhandledEntityTypeException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public UnhandledEntityTypeException() {
        super( Response.status( Status.BAD_REQUEST ).entity( "Entity type not handled" ).type("text/plain").build() );
	}
}
