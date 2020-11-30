package com.template.app.controller;

import com.template.app.model.ApiResponse;
import com.template.app.model.UserDto;
import com.template.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ApiResponse<UserDto> createUser(@RequestBody UserDto user) {
		return new ApiResponse<>(HttpStatus.OK.value(), "User created successfully.", userService.create(user));
	}

	@GetMapping
	public ApiResponse<List<UserDto>> list() {
		return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", userService.findAll());
	}

	@GetMapping("/{userName}")
	public ApiResponse<UserDto> getOne(@PathVariable String userName) {
		Optional<UserDto> userOptional = userService.findOne(userName);
		return userOptional.map(userDto ->
				new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", userDto))
				.orElseGet(() -> new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found.", null));
	}

	@PutMapping("/{user}")
	public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
		return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", userService.update(userDto));
	}

	@DeleteMapping("/{userName}")
	public ApiResponse<Void> delete(@PathVariable String username) {
		userService.delete(username);
		return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
	}
}
