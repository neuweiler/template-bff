package com.template.app.controller;

import com.template.app.model.ApiResponse;
import com.template.app.model.OwnerDto;
import com.template.app.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/owner")
public class OwnerController {

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@PostMapping
	public ApiResponse<OwnerDto> create(@RequestBody OwnerDto owner) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Owner created successfully.", ownerService.create(owner));
	}

	@GetMapping
	public ApiResponse<List<OwnerDto>> list() {
		return new ApiResponse<>(HttpStatus.OK.value(), "Owner list fetched successfully.", ownerService.findAll());
	}

	@GetMapping("/{id}")
	public ApiResponse<OwnerDto> getOne(@PathVariable int id) {
		Optional<OwnerDto> ownerOptional = ownerService.findOne(id);
		return ownerOptional.map(ownerDto ->
				new ApiResponse<>(HttpStatus.OK.value(), "Owner fetched successfully.", ownerDto))
				.orElseGet(() -> new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Owner not found.", null));
	}

	@PutMapping
	public ApiResponse<OwnerDto> update(@RequestBody OwnerDto ownerDto) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Owner updated successfully.", ownerService.update(ownerDto));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<Void> delete(@PathVariable int id) {
		ownerService.delete(id);
		return new ApiResponse<>(HttpStatus.OK.value(), "Owner deleted successfully.", null);
	}
}
