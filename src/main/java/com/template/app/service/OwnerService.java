package com.template.app.service;

import com.template.app.model.OwnerDto;

import java.util.List;
import java.util.Optional;

public interface OwnerService {

	OwnerDto create(OwnerDto ownerDto);

	List<OwnerDto> findAll();

	Optional<OwnerDto> findOne(int id);

	OwnerDto update(OwnerDto ownerDto);

	void delete(int id);
}
