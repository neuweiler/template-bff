package com.template.app.controller;

import com.template.app.model.ApiResponse;
import com.template.app.model.RoleDto;
import com.template.app.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/role")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping
	public ApiResponse<RoleDto> create(@RequestBody RoleDto role) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Role created successfully.", roleService.create(role));
	}

	@GetMapping
	public ApiResponse<List<RoleDto>> list() {
		return new ApiResponse<>(HttpStatus.OK.value(), "Role list fetched successfully.", roleService.findAll());
	}

	@GetMapping("/{id}")
	public ApiResponse<RoleDto> getOne(@PathVariable int id) {
		Optional<RoleDto> roleOptional = roleService.findOne(id);
		return roleOptional.map(roleDto ->
				new ApiResponse<>(HttpStatus.OK.value(), "Role fetched successfully.", roleDto))
				.orElseGet(() -> new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Role not found.", null));
	}

	@PutMapping
	public ApiResponse<RoleDto> update(@RequestBody RoleDto roleDto) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Role updated successfully.", roleService.update(roleDto));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<Void> delete(@PathVariable int id) {
		roleService.delete(id);
		return new ApiResponse<>(HttpStatus.OK.value(), "Role deleted successfully.", null);
	}
}
