package com.template.app.controller;

import com.template.app.model.ApiResponse;
import com.template.app.model.SystemDto;
import com.template.app.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/system")
public class SystemController {

	private final SystemService systemService;

	public SystemController(SystemService systemService) {
		this.systemService = systemService;
	}

	@PostMapping
	public ApiResponse<SystemDto> create(@RequestBody SystemDto system) {
		return new ApiResponse<>(HttpStatus.OK.value(), "System created successfully.", systemService.create(system));
	}

	@GetMapping
	public ApiResponse<List<SystemDto>> list() {
		return new ApiResponse<>(HttpStatus.OK.value(), "System list fetched successfully.", systemService.findAll());
	}

	@GetMapping("/{name}")
	public ApiResponse<SystemDto> getOne(@PathVariable String name) {
		Optional<SystemDto> systemOptional = systemService.findOne(name);
		return systemOptional.map(systemDto ->
				new ApiResponse<>(HttpStatus.OK.value(), "System fetched successfully.", systemDto))
				.orElseGet(() -> new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "System not found.", null));
	}

	@PutMapping("/{name}")
	public ApiResponse<SystemDto> update(@RequestBody SystemDto systemDto) {
		return new ApiResponse<>(HttpStatus.OK.value(), "System updated successfully.", systemService.update(systemDto));
	}

	@DeleteMapping("/{name}")
	public ApiResponse<Void> delete(@PathVariable String name) {
		systemService.delete(name);
		return new ApiResponse<>(HttpStatus.OK.value(), "System deleted successfully.", null);
	}
}
