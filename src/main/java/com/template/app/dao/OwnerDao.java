package com.template.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerDao extends CrudRepository<Owner, Integer> {
	Optional<Owner> findByName(String name);
}
