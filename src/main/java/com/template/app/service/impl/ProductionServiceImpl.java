package com.template.app.service.impl;

import com.googlecode.jmapper.JMapper;
import com.template.app.dao.Production;
import com.template.app.dao.ProductionDao;
import com.template.app.model.ProductionDto;
import com.template.app.service.ProductionService;
import com.template.app.service.ProductionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service(value = "productionService")
public class ProductionServiceImpl implements ProductionService {

	private final ProductionDao productionDao;
	private final JMapper<ProductionDto, Production> mapperToDto = new JMapper<>(ProductionDto.class, Production.class);
	private final JMapper<Production, ProductionDto> mapperFromDto = new JMapper<>(Production.class, ProductionDto.class);

	public ProductionServiceImpl(ProductionDao productionDao) {
		this.productionDao = productionDao;
	}

	@Override
	public ProductionDto create(ProductionDto productionDto) {
		Production production = productionDao.save(mapperFromDto.getDestination(productionDto));
		return mapperToDto.getDestination(production);
	}

	public List<ProductionDto> findAll() {
		List<Production> list = new ArrayList<>();
		productionDao.findAll().iterator().forEachRemaining(list::add);
		return list.stream().map(mapperToDto::getDestination)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ProductionDto> findOne(long id) {
		return productionDao.findById(id).map(mapperToDto::getDestination);
	}

	@Override
	public ProductionDto update(ProductionDto productionDto) {
		Optional<Production> optionalProduction = productionDao.findById(productionDto.getId());
		optionalProduction.ifPresent(production -> {
			BeanUtils.copyProperties(productionDto, production);
			productionDao.save(production);
		});
		return productionDto;
	}

	@Override
	public void delete(long id) {
		productionDao.deleteById(id);
	}
}
