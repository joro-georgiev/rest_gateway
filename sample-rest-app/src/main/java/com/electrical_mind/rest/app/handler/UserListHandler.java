package com.electrical_mind.rest.app.handler;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;

import com.electrical_mind.rest.app.entity.User;
import com.electrical_mind.rest.service.context.handler.EntityListHandler;

public class UserListHandler extends EntityListHandler<User> {

	@Override
	@GET
	public List<Object> listEntities() {
		return new ArrayList<>();
	}
	
	@Override
	@PUT
	public Object createEntity(User entityData) {
		return null;
	}
}
