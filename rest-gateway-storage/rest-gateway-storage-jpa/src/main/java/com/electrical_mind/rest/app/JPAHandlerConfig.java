package com.electrical_mind.rest.app;

import com.electrical_mind.rest.service.context.EntityHandlerConfig;
import com.electrical_mind.rest.service.context.handler.EntityHandler;
import com.electrical_mind.rest.service.context.handler.EntityListHandler;
import com.electrical_mind.rest.service.context.handler.JPAEntityHandler;
import com.electrical_mind.rest.service.context.handler.JPAEntityListHandler;


public class JPAHandlerConfig extends EntityHandlerConfig {

	@SuppressWarnings("unchecked")
	public  static <T> EntityHandlerConfig defaultConfig ( String entityType, Class<T> entityClass ) {
		
		Class<? extends EntityListHandler<T>> listHandlerClass = (Class<? extends EntityListHandler<T>>) new JPAEntityListHandler<T>().getClass();
		Class<? extends EntityHandler<T>> handlerClass = (Class<? extends EntityHandler<T>>)  new JPAEntityHandler<T>().getClass();
		
		return 
			new JPAHandlerConfig()
				.entityType(entityType)
				.entityClass(entityClass)
				.listHandler( listHandlerClass )
				.entityHandler( handlerClass )
			;
	}
	
}
