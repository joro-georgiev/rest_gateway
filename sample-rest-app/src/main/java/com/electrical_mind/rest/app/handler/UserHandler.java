package com.electrical_mind.rest.app.handler;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import com.electrical_mind.rest.app.entity.User;
import com.electrical_mind.rest.service.context.handler.EntityHandler;


public class UserHandler extends EntityHandler<User> {

	@Override
	@GET
	public User getEntity() {
		User user = new User();
		user.setEmail( getId() );
		user.setName( "test:" + getId() );
		return user;
	}

	@Override
	@POST
	public Object updateEntity(User entityData) {
		System.out.println( entityData );
		return entityData;
	}

	@Override
	@DELETE
	public void deleteEntity() {
		
	}

}
