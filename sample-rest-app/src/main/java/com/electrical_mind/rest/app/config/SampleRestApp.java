package com.electrical_mind.rest.app.config;

import com.electrical_mind.rest.app.JPAHandlerConfig;
import com.electrical_mind.rest.app.entity.Rule;
import com.electrical_mind.rest.app.entity.User;
import com.electrical_mind.rest.app.handler.UserHandler;
import com.electrical_mind.rest.app.handler.UserListHandler;
import com.electrical_mind.rest.guice.GuiceModuleBuilder;
import com.electrical_mind.rest.service.context.EntityHandlerConfig;
import com.google.inject.Module;

public class SampleRestApp {

	
	public static class Injections {
		
		private static final String PERSISTENCE_UNIT = "sample_rest_app";

		public static Module getModule() {
			return new GuiceModuleBuilder().loggingSupport().jpaSupport( PERSISTENCE_UNIT ).entityRegistry( getConfigs() ).build();
		}

		public static Module getModule( EntityHandlerConfig... configs ) {
			return new GuiceModuleBuilder().loggingSupport().jpaSupport( PERSISTENCE_UNIT ).entityRegistry( configs ).build();
		}
		
		private static EntityHandlerConfig[] getConfigs() {
			return new EntityHandlerConfig[] {
				new EntityHandlerConfig( "users", User.class ).listHandler( UserListHandler.class ).entityHandler( UserHandler.class ),
				JPAHandlerConfig.defaultConfig( "rules", Rule.class )
			};
		}
	}
}
