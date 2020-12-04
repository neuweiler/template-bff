package com.template.app.service;

import com.template.app.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

	UserDto create(UserDto user);

	List<UserDto> findAll();

	Optional<UserDto> findOne(int id);

	UserDto update(UserDto userDto);

	void delete(int id);
}
