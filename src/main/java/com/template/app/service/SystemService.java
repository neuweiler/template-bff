package com.template.app.service;

import com.template.app.model.SystemDto;

import java.util.List;
import java.util.Optional;

public interface SystemService {

	SystemDto create(SystemDto systemDto);

	List<SystemDto> findAll();

	Optional<SystemDto> findOne(String name);

	SystemDto update(SystemDto systemDto);

	void delete(String name);
}
