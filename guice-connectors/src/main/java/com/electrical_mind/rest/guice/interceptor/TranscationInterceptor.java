package com.electrical_mind.rest.guice.interceptor;

import javax.persistence.EntityManager;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.electrical_mind.rest.service.aspect.PersistenceService;
import com.electrical_mind.rest.service.aspect.TransactionalAspect;
import com.electrical_mind.rest.service.aspect.TransactionalAspect.TransactionalTask;


public class TranscationInterceptor implements MethodInterceptor {

	@Override
	public Object invoke( final MethodInvocation invocation ) throws Throwable {
		
		if ( !(invocation.getThis() instanceof PersistenceService) ) {
			throw new IllegalStateException( "Annotation @Transactional shoud be applied only to instances of PersistenceService" );
		}
		
		return new TransactionalAspect().supportTransaction( new TransactionalTask() {
			
			@Override
			public String name() {
				return invocation.getMethod().getName();
			}
			
			@Override
			public Object execute() throws Throwable {
				return invocation.proceed();
			}
			
			@Override
			public EntityManager entityManager() {
				return ( (PersistenceService) invocation.getThis() ).entityManager();
			}
		} );
	}

}
