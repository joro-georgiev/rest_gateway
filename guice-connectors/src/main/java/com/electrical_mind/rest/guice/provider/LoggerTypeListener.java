package com.electrical_mind.rest.guice.provider;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.log4j.Logger;

import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

public class LoggerTypeListener implements TypeListener {
	
	static class Log4JMembersInjector<T> implements MembersInjector<T> {
		
		private final Field field;
		
		private final Logger logger;

		Log4JMembersInjector(Class<?> clazz, Field field) {
			this.field = field;
			this.logger = Logger.getLogger( clazz );
		}

		public void injectMembers(final T t) {

			AccessController.doPrivileged(new PrivilegedAction<Void>() {

				@Override
				public Void run() {
					try {
						field.setAccessible(true);
						field.set(t, logger);
						return null;

					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			});
		}
	}

	@Override
	public <T> void hear(
			TypeLiteral<T> typeLiteral,
			TypeEncounter<T> typeEncounter) {

		for( Class<?> clazz = typeLiteral.getRawType(); clazz != Object.class; clazz = clazz.getSuperclass() ) {
			walk( clazz, typeLiteral.getRawType(), typeEncounter );
		}
	}
	
	private <T> void walk( Class<?> clazz, Class<?> actualType, TypeEncounter<T> typeEncounter ) {
		for ( Field field : clazz.getDeclaredFields() ) {
			if (field.getType() == Logger.class) {
				typeEncounter.register( new Log4JMembersInjector<T>(actualType, field) );
			}
		}
	}

}
