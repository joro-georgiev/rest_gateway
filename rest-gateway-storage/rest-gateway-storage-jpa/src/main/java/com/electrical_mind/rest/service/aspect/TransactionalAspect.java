package com.electrical_mind.rest.service.aspect;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;


public class TransactionalAspect {

	private static final Logger LOG = Logger.getLogger( TransactionalAspect.class );
	
	
	public static interface TransactionalTask {
	
		String name();
		
		Object execute() throws Throwable;
		
		EntityManager entityManager();
	}
	
	
	public Object supportTransaction( TransactionalTask task ) throws Throwable {
		
		LOG.info( "Executing in transaction: " + task.name() );
		
		EntityTransaction t = task.entityManager().getTransaction();
		
		if ( t.isActive() ) {
			LOG.info( "Transaction already active. Continuing: " + task.name() );
			return task.execute();
		}
		
		try {
			t.begin();
			
			Object result = task.execute();
			
			t.commit();
			
			LOG.info( task.name() + " executed successfuly" );
			
			return result;
		}
		finally {
			if ( t.isActive() ) {
				LOG.info( task.name() + " rollbacked" );
				t.rollback();
			}
		}
	}

}
