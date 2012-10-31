package com.electrical_mind.rest.app.config;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;


public class ApplicationContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector( new SampleRestAppModule(), jerseyServletModule() );
	}
	
	private JerseyServletModule jerseyServletModule() {
		return new JerseyServletModule() {
			
			@Override
			protected void configureServlets() {
				
				Map<String, String> params = new HashMap<String, String>();
                params.put( "com.sun.jersey.api.json.POJOMappingFeature", "true" );
                params.put( "com.sun.jersey.config.property.packages", "com.electrical_mind.rest.jee6.service" );
				
	            serve("/rest/*").with(GuiceContainer.class, params);
			}
		};
	}

}
