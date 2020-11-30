package com.template.app.service.impl;

import com.template.app.dao.System;
import com.template.app.service.SystemService;
import com.template.app.dao.SystemDao;
import com.template.app.model.SystemDto;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service(value = "systemService")
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemDao systemDao;

	JMapper<SystemDto, System> mapperToDto = new JMapper<>(SystemDto.class, System.class);
	JMapper<System, SystemDto> mapperFromDto = new JMapper<>(System.class, SystemDto.class);

	@Override
	public SystemDto create(SystemDto systemDto) {
		System system = systemDao.save(mapperFromDto.getDestination(systemDto));
		return mapperToDto.getDestination(system);
	}

	public List<SystemDto> findAll() {
		List<System> list = new ArrayList<>();
		systemDao.findAll().iterator().forEachRemaining(list::add);
		return list.stream().map(system -> mapperToDto.getDestination(system))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<SystemDto> findOne(String name) {
		return systemDao.findByName(name).map(system -> mapperToDto.getDestination(system));
	}

	@Override
	public SystemDto update(SystemDto systemDto) {
		Optional<System> optionalSystem = systemDao.findByName(systemDto.getName());
		optionalSystem.ifPresent(system -> {
			BeanUtils.copyProperties(systemDto, system);
			systemDao.save(system);
		});
		return systemDto;
	}

	@Override
	public void delete(String name) {
		Optional<System> optionalSystem = systemDao.findByName(name);
		optionalSystem.ifPresent(system -> systemDao.deleteById(system.getId()));
	}
}
