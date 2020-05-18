package com.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.userservice.entity.User;
import com.userservice.service.UserService;
import com.userservice.dto.UserRequestBody;
import com.userservice.dto.UserServiceResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private static UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(path = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserServiceResponse getUser(@PathVariable(value = "userName") String userName) {
		return new UserServiceResponse(userService.getByUserName(userName));
	}

	@DeleteMapping(path = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserServiceResponse deleteUser(@PathVariable("userName") String userName) {
		userService.deleteUser(userName);
		return new UserServiceResponse("User successfully deleted.");
	}
	
	@PostMapping(path = "/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public UserServiceResponse createUser(@PathVariable("userName") String userName,
			@RequestBody UserRequestBody request) {
		userService.createUser(userName, request);
		return new UserServiceResponse("User successfully created.");
	}

	@PutMapping(path = "/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserServiceResponse updateUser(@PathVariable("userName") String userName,
			@RequestBody UserRequestBody request) {
		userService.updateUser(userName, request);
		return new UserServiceResponse("User successfully updated.");
	}
}