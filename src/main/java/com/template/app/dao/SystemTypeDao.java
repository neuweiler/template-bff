package com.template.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemTypeDao extends CrudRepository<SystemType, Integer> {
	SystemType findByCode(String code);
}
