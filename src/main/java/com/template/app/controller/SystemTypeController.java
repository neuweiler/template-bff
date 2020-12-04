package com.template.app.controller;

import com.template.app.model.ApiResponse;
import com.template.app.model.SystemTypeDto;
import com.template.app.service.SystemTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/systemType")
public class SystemTypeController {

	private final SystemTypeService systemTypeService;

	public SystemTypeController(SystemTypeService systemTypeService) {
		this.systemTypeService = systemTypeService;
	}

	@PostMapping
	public ApiResponse<SystemTypeDto> create(@RequestBody SystemTypeDto systemType) {
		return new ApiResponse<>(HttpStatus.OK.value(), "SystemType created successfully.", systemTypeService.create(systemType));
	}

	@GetMapping
	public ApiResponse<List<SystemTypeDto>> list() {
		return new ApiResponse<>(HttpStatus.OK.value(), "SystemType list fetched successfully.", systemTypeService.findAll());
	}

	@GetMapping("/{id}")
	public ApiResponse<SystemTypeDto> getOne(@PathVariable int id) {
		Optional<SystemTypeDto> systemTypeOptional = systemTypeService.findOne(id);
		return systemTypeOptional.map(systemTypeDto ->
				new ApiResponse<>(HttpStatus.OK.value(), "SystemType fetched successfully.", systemTypeDto))
				.orElseGet(() -> new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "SystemType not found.", null));
	}

	@PutMapping
	public ApiResponse<SystemTypeDto> update(@RequestBody SystemTypeDto systemTypeDto) {
		return new ApiResponse<>(HttpStatus.OK.value(), "SystemType updated successfully.", systemTypeService.update(systemTypeDto));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<Void> delete(@PathVariable int id) {
		systemTypeService.delete(id);
		return new ApiResponse<>(HttpStatus.OK.value(), "SystemType deleted successfully.", null);
	}
}
