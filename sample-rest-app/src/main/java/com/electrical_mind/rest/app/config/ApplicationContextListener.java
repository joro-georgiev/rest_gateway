package com.electrical_mind.rest.app.config;

import java.util.HashMap;
import java.util.Map;

import com.electrical_mind.rest.service.EntityService;
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
				
				bind( EntityService.class );
				
				Map<String, String> params = new HashMap<String, String>();
                params.put( "com.sun.jersey.api.json.POJOMappingFeature", "true");
				
	            serve("/rest/*").with(GuiceContainer.class, params);
			}
		};
	}

}
