package com.electrical_mind.rest.service.context;

import java.util.HashMap;
import java.util.Map;

import com.electrical_mind.rest.service.context.handler.EntityHandler;
import com.electrical_mind.rest.service.context.handler.EntityListHandler;



public class EntityRegistry {

	private Map<String, EntityHandlerConfig> handlers = new HashMap<>();
	
	
	public Class<? extends EntityHandler<?>> getHandler( String entityType ) {
		return handlers.get(entityType) != null ? handlers.get(entityType).getEntityHandler() : null;
	}
	
	public Class<? extends EntityListHandler<?>> getListHandler( String entityType ) {
		return handlers.get(entityType) != null ? handlers.get(entityType).getListHandler() : null;
	}

	public EntityHandlerConfig getConfig( String entityType ) {
		return handlers.get(entityType);
	}
	
	public void addHandler( EntityHandlerConfig config ) {
		handlers.put( config.getEntityType(), config );
	}
	
}
