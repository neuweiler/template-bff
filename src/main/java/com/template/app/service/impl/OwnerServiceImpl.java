package com.template.app.service.impl;

import com.googlecode.jmapper.JMapper;
import com.template.app.dao.Owner;
import com.template.app.dao.OwnerDao;
import com.template.app.model.OwnerDto;
import com.template.app.service.OwnerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service(value = "ownerService")
public class OwnerServiceImpl implements OwnerService {

	private final OwnerDao ownerDao;
	private final JMapper<OwnerDto, Owner> mapperToDto = new JMapper<>(OwnerDto.class, Owner.class);
	private final JMapper<Owner, OwnerDto> mapperFromDto = new JMapper<>(Owner.class, OwnerDto.class);

	public OwnerServiceImpl(OwnerDao ownerDao) {
		this.ownerDao = ownerDao;
	}

	@Override
	public OwnerDto create(OwnerDto ownerDto) {
		Owner owner = ownerDao.save(mapperFromDto.getDestination(ownerDto));
		return mapperToDto.getDestination(owner);
	}

	public List<OwnerDto> findAll() {
		List<Owner> list = new ArrayList<>();
		ownerDao.findAll().iterator().forEachRemaining(list::add);
		return list.stream().map(mapperToDto::getDestination)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<OwnerDto> findOne(int id) {
		return ownerDao.findById(id).map(mapperToDto::getDestination);
	}

	@Override
	public OwnerDto update(OwnerDto ownerDto) {
		Optional<Owner> optionalOwner = ownerDao.findById(ownerDto.getId());
		optionalOwner.ifPresent(owner -> {
			BeanUtils.copyProperties(ownerDto, owner);
			ownerDao.save(owner);
		});
		return ownerDto;
	}

	@Override
	public void delete(int id) {
		ownerDao.deleteById(id);
	}
}
