package com.electrical_mind.rest.app.handler;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;

import com.electrical_mind.rest.app.entity.User;
import com.electrical_mind.rest.service.context.handler.EntityListHandler;

public class UserListHandler extends EntityListHandler<User> {

	@Override
	@GET
	public List<Object> listEntities() {
		return new ArrayList<>();
	}
	
	@Override
	public Object doCreateEntity( User user ) {
		return user;
	}
}
