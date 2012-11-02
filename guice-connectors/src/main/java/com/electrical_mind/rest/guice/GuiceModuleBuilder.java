package com.electrical_mind.rest.guice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.electrical_mind.rest.guice.interceptor.TranscationInterceptor;
import com.electrical_mind.rest.guice.provider.EntityManagerProvider;
import com.electrical_mind.rest.guice.provider.LoggerTypeListener;
import com.electrical_mind.rest.service.context.EntityHandlerConfig;
import com.electrical_mind.rest.service.context.EntityRegistry;
import com.electrical_mind.rest.service.context.RestServiceContext;
import com.electrical_mind.rest.service.context.annotation.Transactional;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;


public class GuiceModuleBuilder {

	public static interface ModuleBinding {
		void bind( Binder binder );
	}
	
	
	private final List<ModuleBinding> bindings = new ArrayList<>();
	
	
	public GuiceModuleBuilder loggingSupport() {
		bindings.add( new LoggingBinding() );
		return this;
	}
	
	public GuiceModuleBuilder jpaSupport( String unitName ) {
		bindings.add( new JPABinding(unitName) );
		return this;
	}
	
	public GuiceModuleBuilder entityRegistry( EntityHandlerConfig... configs ) {
		bindings.add( new EtityRegistryBinding( configs ) );
		return this;
	}
	
	
	public Module build() {
		return new Module() {
			
			@Override
			public void configure( Binder binder ) {
				for( ModuleBinding binding : bindings ) {
					binding.bind(binder);
				}
			}
		};
	}
	
	private static class EtityRegistryBinding implements ModuleBinding {

		private EntityHandlerConfig[] configs;
		
		public EtityRegistryBinding(EntityHandlerConfig[] configs) {
			this.configs = configs;
		}

		@Override
		public void bind(Binder binder) {
			binder.bind( RestServiceContext.class ).toInstance( restServiceContext() );
		}

		private RestServiceContext restServiceContext() {
			RestServiceContext context = new RestServiceContext();
			context.setEntityRegistry( buildEntityRegistry() );
			return context;
		}

		private EntityRegistry buildEntityRegistry() {
			EntityRegistry registry = new EntityRegistry();
			for ( EntityHandlerConfig config : configs ) {
				registry.addHandler( config );
			}
			return registry;
		}
		
	}
	
	private static class JPABinding implements ModuleBinding {
		
		private String unitName;

		public JPABinding(String unitName) {
			this.unitName = unitName;
		}

		@Override
		public void bind(Binder binder) {
			binder.bind( EntityManager.class ).toProvider( new EntityManagerProvider(unitName) );
			binder.bindInterceptor( Matchers.any(), Matchers.annotatedWith( Transactional.class), new TranscationInterceptor() );
		}
	}
	
	private static class LoggingBinding implements ModuleBinding {
		
		@Override
		public void bind(Binder binder) {
			binder.bind( Logger.class ).toInstance( Logger.getRootLogger() );
			binder.bindListener( Matchers.any(), new LoggerTypeListener() );
		}
	}
	
}
