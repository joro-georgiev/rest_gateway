package com.electrical_mind.rest.service.aspect;

import javax.persistence.EntityTransaction;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;


public class TransactionalAspect implements MethodInterceptor {

	private static final Logger LOG = Logger.getLogger( TransactionalAspect.class );
	
	@Override
	public Object invoke( MethodInvocation invocation ) throws Throwable {
		
		if ( !(invocation.getThis() instanceof PersistenceService) ) {
			throw new IllegalStateException( "Annotation @Transactional shoud be applied only to instances of PersistenceService" );
		}
		
		LOG.info( "Executing in transaction: " + invocation.getMethod().getName() );
		
		PersistenceService ps = (PersistenceService) invocation.getThis();
		
		EntityTransaction t = ps.entityManager().getTransaction();
		
		if ( t.isActive() ) {
			return invocation.proceed();
		}
		
		try {
			t.begin();
			
			Object result = invocation.proceed();
			
			t.commit();
			
			return result;
		}
		finally {
			if ( t.isActive() ) {
				t.rollback();
			}
		}
	}

}
