package com.template.app.service;

import com.template.app.model.SystemTypeDto;

import java.util.List;
import java.util.Optional;

public interface SystemTypeService {

	SystemTypeDto create(SystemTypeDto systemTypeDto);

	List<SystemTypeDto> findAll();

	Optional<SystemTypeDto> findOne(int id);

	SystemTypeDto update(SystemTypeDto systemTypeDto);

	void delete(int id);
}
