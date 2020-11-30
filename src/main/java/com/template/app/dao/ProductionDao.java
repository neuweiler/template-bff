package com.template.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionDao extends CrudRepository<Production, Long> {
	Production findBySystem(System system);
}
