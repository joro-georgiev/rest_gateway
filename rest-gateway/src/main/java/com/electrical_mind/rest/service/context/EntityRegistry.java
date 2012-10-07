package com.electrical_mind.rest.service.context;

import java.util.HashMap;
import java.util.Map;

import com.electrical_mind.rest.service.context.handler.EntityHandler;
import com.electrical_mind.rest.service.context.handler.EntityListHandler;



public class EntityRegistry {

	private static class EntityHandlers {
		
		private Class<? extends EntityHandler<?>> handler;
		
		private Class<? extends EntityListHandler<?>> listHandler;

		public EntityHandlers(Class<? extends EntityHandler<?>> handler, Class<? extends EntityListHandler<?>> listHandler) {
			this.handler = handler;
			this.listHandler = listHandler;
		}

		public Class<? extends EntityHandler<?>> getHandler() {
			return handler;
		}

		public Class<? extends EntityListHandler<?>> getListHandler() {
			return listHandler;
		}
	}
	
	
	private Map<String, EntityHandlers> handlers = new HashMap<>();
	
	public Class<? extends EntityHandler<?>> getHandler( String entityType ) {
		return handlers.get(entityType) != null ? handlers.get(entityType).getHandler() : null;
	}
	
	public Class<? extends EntityListHandler<?>> getListHandler( String entityType ) {
		return handlers.get(entityType) != null ? handlers.get(entityType).getListHandler() : null;
	}
	
	public EntityRegistry addHandlers( String entityType, Class<? extends EntityListHandler<?>> listHandler, Class<? extends EntityHandler<?>> handler ) {
		handlers.put( entityType, new EntityHandlers(handler, listHandler) );
		return this;
	}
}
