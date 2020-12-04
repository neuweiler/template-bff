package com.template.app.service;

import com.template.app.model.RoleDto;

import java.util.List;
import java.util.Optional;

public interface RoleService {

	RoleDto create(RoleDto roleDto);

	List<RoleDto> findAll();

	Optional<RoleDto> findOne(int id);

	RoleDto update(RoleDto roleDto);

	void delete(int id);
}
