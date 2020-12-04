package com.template.app.service;

import com.template.app.model.ProductionDto;

import java.util.List;
import java.util.Optional;

public interface ProductionService {

	ProductionDto create(ProductionDto productionDto);

	List<ProductionDto> findAll();

	Optional<ProductionDto> findOne(long id);

	ProductionDto update(ProductionDto productionDto);

	void delete(long id);
}
