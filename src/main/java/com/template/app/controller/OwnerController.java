package com.template.app.controller;

import com.template.app.model.ApiResponse;
import com.template.app.model.OwnerDto;
import com.template.app.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;

	@PostMapping
	public ApiResponse<OwnerDto> create(@RequestBody OwnerDto owner) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Owner created successfully.", ownerService.create(owner));
	}

	@GetMapping
	public ApiResponse<List<OwnerDto>> list() {
		return new ApiResponse<>(HttpStatus.OK.value(), "Owner list fetched successfully.", ownerService.findAll());
	}

	@GetMapping("/{name}")
	public ApiResponse<OwnerDto> getOne(@PathVariable String name) {
		Optional<OwnerDto> ownerOptional = ownerService.findOne(name);
		return ownerOptional.map(ownerDto ->
				new ApiResponse<>(HttpStatus.OK.value(), "Owner fetched successfully.", ownerDto))
				.orElseGet(() -> new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Owner not found.", null));
	}

	@PutMapping("/{name}")
	public ApiResponse<OwnerDto> update(@RequestBody OwnerDto ownerDto) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Owner updated successfully.", ownerService.update(ownerDto));
	}

	@DeleteMapping("/{name}")
	public ApiResponse<Void> delete(@PathVariable String name) {
		ownerService.delete(name);
		return new ApiResponse<>(HttpStatus.OK.value(), "Owner deleted successfully.", null);
	}
}
