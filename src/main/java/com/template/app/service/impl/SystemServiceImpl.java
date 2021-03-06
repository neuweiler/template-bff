package com.template.app.service.impl;

import com.googlecode.jmapper.JMapper;
import com.template.app.dao.System;
import com.template.app.dao.SystemDao;
import com.template.app.model.SystemDto;
import com.template.app.service.SystemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service(value = "systemService")
public class SystemServiceImpl implements SystemService {

	private final SystemDao systemDao;
	private final JMapper<SystemDto, System> mapperToDto = new JMapper<>(SystemDto.class, System.class);
	private final JMapper<System, SystemDto> mapperFromDto = new JMapper<>(System.class, SystemDto.class);

	public SystemServiceImpl(SystemDao systemDao) {
		this.systemDao = systemDao;
	}

	@Override
	public SystemDto create(SystemDto systemDto) {
		System system = systemDao.save(mapperFromDto.getDestination(systemDto));
		return mapperToDto.getDestination(system);
	}

	public List<SystemDto> findAll() {
		List<System> list = new ArrayList<>();
		systemDao.findAll().iterator().forEachRemaining(list::add);
		return list.stream().map(mapperToDto::getDestination)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<SystemDto> findOne(int id) {
		return systemDao.findById(id).map(mapperToDto::getDestination);
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
	public void delete(int id) {
		systemDao.deleteById(id);
	}
}
