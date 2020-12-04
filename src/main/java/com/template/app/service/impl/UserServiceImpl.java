package com.template.app.service.impl;

import com.googlecode.jmapper.JMapper;
import com.template.app.dao.User;
import com.template.app.dao.UserDao;
import com.template.app.model.UserDto;
import com.template.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final JMapper<UserDto, User> mapperToDto = new JMapper<>(UserDto.class, User.class);
	private final JMapper<User, UserDto> mapperFromDto = new JMapper<>(User.class, UserDto.class);

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDto create(UserDto userDto) {
		User user = userDao.save(mapperFromDto.getDestination(userDto));
		return mapperToDto.getDestination(user);
	}

	public List<UserDto> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list.stream().map(mapperToDto::getDestination)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<UserDto> findOne(int id) {
		return userDao.findById(id).map(mapperToDto::getDestination);
	}

	@Override
	public UserDto update(UserDto userDto) {
		Optional<User> optionalUser = userDao.findByUserName(userDto.getUserName());
		optionalUser.ifPresent(user -> {
			BeanUtils.copyProperties(userDto, user, "password");
			userDao.save(user);
		});
		return userDto;
	}

	@Override
	public void delete(int id) {
		userDao.deleteById(id);
	}
}
