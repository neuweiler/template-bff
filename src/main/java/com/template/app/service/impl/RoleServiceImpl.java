package com.template.app.service.impl;

import com.googlecode.jmapper.JMapper;
import com.template.app.dao.Role;
import com.template.app.dao.RoleDao;
import com.template.app.model.RoleDto;
import com.template.app.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;
	private final JMapper<RoleDto, Role> mapperToDto = new JMapper<>(RoleDto.class, Role.class);
	private final JMapper<Role, RoleDto> mapperFromDto = new JMapper<>(Role.class, RoleDto.class);

	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public RoleDto create(RoleDto roleDto) {
		Role role = roleDao.save(mapperFromDto.getDestination(roleDto));
		return mapperToDto.getDestination(role);
	}

	public List<RoleDto> findAll() {
		List<Role> list = new ArrayList<>();
		roleDao.findAll().iterator().forEachRemaining(list::add);
		return list.stream().map(mapperToDto::getDestination)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<RoleDto> findOne(int id) {
		return roleDao.findById(id).map(mapperToDto::getDestination);
	}

	@Override
	public RoleDto update(RoleDto roleDto) {
		Optional<Role> optionalRole = roleDao.findById(roleDto.getId());
		optionalRole.ifPresent(role -> {
			BeanUtils.copyProperties(roleDto, role);
			roleDao.save(role);
		});
		return roleDto;
	}

	@Override
	public void delete(int id) {
		roleDao.deleteById(id);
	}
}
