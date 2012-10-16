package com.electrical_mind.rest.service.aspect;

import javax.persistence.EntityManager;

public interface PersistenceService {

	EntityManager entityManager();
}
