package com.template.app.service.impl;

import com.template.app.dao.Owner;
import com.template.app.dao.OwnerDao;
import com.template.app.model.OwnerDto;
import com.template.app.service.OwnerService;
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
@Service(value = "ownerService")
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	JMapper<OwnerDto, Owner> mapperToDto = new JMapper<>(OwnerDto.class, Owner.class);
	JMapper<Owner, OwnerDto> mapperFromDto = new JMapper<>(Owner.class, OwnerDto.class);

	@Override
	public OwnerDto create(OwnerDto ownerDto) {
		Owner owner = ownerDao.save(mapperFromDto.getDestination(ownerDto));
		return mapperToDto.getDestination(owner);
	}

	public List<OwnerDto> findAll() {
		List<Owner> list = new ArrayList<>();
		ownerDao.findAll().iterator().forEachRemaining(list::add);
		return list.stream().map(owner -> mapperToDto.getDestination(owner))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<OwnerDto> findOne(String name) {
		return ownerDao.findByName(name).map(owner -> mapperToDto.getDestination(owner));
	}

	@Override
	public OwnerDto update(OwnerDto ownerDto) {
		Optional<Owner> optionalOwner = ownerDao.findByName(ownerDto.getName());
		optionalOwner.ifPresent(owner -> {
			BeanUtils.copyProperties(ownerDto, owner);
			ownerDao.save(owner);
		});
		return ownerDto;
	}

	@Override
	public void delete(String name) {
		Optional<Owner> optionalOwner = ownerDao.findByName(name);
		optionalOwner.ifPresent(owner -> ownerDao.deleteById(owner.getId()));
	}
}
