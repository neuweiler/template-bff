package com.template.app.service.impl;

import com.googlecode.jmapper.JMapper;
import com.template.app.dao.SystemType;
import com.template.app.dao.SystemTypeDao;
import com.template.app.model.SystemTypeDto;
import com.template.app.service.SystemTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service(value = "systemTypeService")
public class SystemTypeServiceImpl implements SystemTypeService {

	private final SystemTypeDao systemTypeDao;
	private final JMapper<SystemTypeDto, SystemType> mapperToDto = new JMapper<>(SystemTypeDto.class, SystemType.class);
	private final JMapper<SystemType, SystemTypeDto> mapperFromDto = new JMapper<>(SystemType.class, SystemTypeDto.class);

	public SystemTypeServiceImpl(SystemTypeDao systemTypeDao) {
		this.systemTypeDao = systemTypeDao;
	}

	@Override
	public SystemTypeDto create(SystemTypeDto systemTypeDto) {
		SystemType systemType = systemTypeDao.save(mapperFromDto.getDestination(systemTypeDto));
		return mapperToDto.getDestination(systemType);
	}

	public List<SystemTypeDto> findAll() {
		List<SystemType> list = new ArrayList<>();
		systemTypeDao.findAll().iterator().forEachRemaining(list::add);
		return list.stream().map(mapperToDto::getDestination)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<SystemTypeDto> findOne(int id) {
		return systemTypeDao.findById(id).map(mapperToDto::getDestination);
	}

	@Override
	public SystemTypeDto update(SystemTypeDto systemTypeDto) {
		Optional<SystemType> optionalSystemType = systemTypeDao.findById(systemTypeDto.getId());
		optionalSystemType.ifPresent(systemType -> {
			BeanUtils.copyProperties(systemTypeDto, systemType);
			systemTypeDao.save(systemType);
		});
		return systemTypeDto;
	}

	@Override
	public void delete(int id) {
		systemTypeDao.deleteById(id);
	}
}
