package com.electrical_mind;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.electrical_mind.rest.PersistenceTestModule;
import com.electrical_mind.rest.app.entity.User;
import com.electrical_mind.rest.service.context.handler.JPAEntityHandler;
import com.electrical_mind.rest.service.context.handler.JPAEntityListHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PersistenceTest {

	private static final String ENTITY_TYPE = "user";
	
	private static JPAEntityListHandler<User> userListHandler;
	
	private static JPAEntityHandler<User> userHandler;
	
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void init() {
		Injector injector = Guice.createInjector( new PersistenceTestModule( ENTITY_TYPE ) );
		
		userListHandler = injector.getInstance( JPAEntityListHandler.class );
		userListHandler.setEntityClass( User.class );
		
		userHandler = injector.getInstance( JPAEntityHandler.class );
		userHandler.setEntityClass( User.class );
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void listEntitiesTest() {
		List<User> users = (List<User>) userListHandler.listEntities();
		
		assertTrue( users.isEmpty() );
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void addEntityTest() {
		
		User user = new User();
		user.setName( "test" );
		user.setEmail( "test@example.org" );
		
		assertNotNull( userListHandler.doCreateEntity( user ) );
		
		List<User> users = (List<User>) userListHandler.listEntities();
		
		assertFalse( users.isEmpty() );
		
		assertEquals( 1, users.size() );
		
		User loaded = users.get( 0 );
		assertNotNull( loaded );
		
		userHandler.setId( loaded.getId() );
		userHandler.deleteEntity();
		
		List<User> allUsers = (List<User>) userListHandler.listEntities();
		
		assertTrue( allUsers.isEmpty() );
	}
}
