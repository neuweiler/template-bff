package com.template.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemDao extends CrudRepository<System, Integer> {
	Optional<System> findByName(String name);
}
