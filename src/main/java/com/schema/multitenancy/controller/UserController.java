package com.schema.multitenancy.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.schema.multitenancy.model.User;
import com.schema.multitenancy.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	@ResponseStatus(CREATED)
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User newUser = userService.createUser(user);

		return ResponseEntity.ok(newUser);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {

		List<User> users = userService.getAllUsers();

		return ResponseEntity.ok(users);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {

		User user = userService.getUser(id);

		return ResponseEntity.ok(user);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") UUID id) {

		User updatedUser = userService.updateUser(user, id);

		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {

		userService.deleteUser(id);

		return ResponseEntity.ok("");
	}

	@DeleteMapping("/")
	public ResponseEntity<String> deleteAllUsers() {

		userService.deleteAllUsers();

		return ResponseEntity.ok("");
	}

}
