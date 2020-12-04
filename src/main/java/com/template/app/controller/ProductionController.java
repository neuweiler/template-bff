package com.template.app.controller;

import com.template.app.model.ApiResponse;
import com.template.app.model.ProductionDto;
import com.template.app.service.ProductionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/production")
public class ProductionController {

	private final ProductionService productionService;

	public ProductionController(ProductionService productionService) {
		this.productionService = productionService;
	}

	@PostMapping
	public ApiResponse<ProductionDto> create(@RequestBody ProductionDto production) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Production created successfully.", productionService.create(production));
	}

	@GetMapping
	public ApiResponse<List<ProductionDto>> list() {
		return new ApiResponse<>(HttpStatus.OK.value(), "Production list fetched successfully.", productionService.findAll());
	}

	@GetMapping("/{id}")
	public ApiResponse<ProductionDto> getOne(@PathVariable int id) {
		Optional<ProductionDto> productionOptional = productionService.findOne(id);
		return productionOptional.map(productionDto ->
				new ApiResponse<>(HttpStatus.OK.value(), "Production fetched successfully.", productionDto))
				.orElseGet(() -> new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Production not found.", null));
	}

	@PutMapping
	public ApiResponse<ProductionDto> update(@RequestBody ProductionDto productionDto) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Production updated successfully.", productionService.update(productionDto));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<Void> delete(@PathVariable int id) {
		productionService.delete(id);
		return new ApiResponse<>(HttpStatus.OK.value(), "Production deleted successfully.", null);
	}
}
