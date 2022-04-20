package com.prakash.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prakash.dao.UserRepositoryService;
import com.prakash.exception.UserNotFoundException;
import com.prakash.model.Post;
import com.prakash.model.User;
import com.prakash.service.UserService;

@RestController
public class UserJpaController {

	@Autowired
	private UserService userService;

	// Hal exposure http://localhost:8099/
	// Hal exposure http://localhost:8099/actuator

	@Autowired
	private UserRepositoryService userRepositoryService;

	@GetMapping(path = "/jpa/user")
	public List<User> findAll() {
		return userRepositoryService.findAll();
	}

	@GetMapping(path = "/jpa/user/{id}")
	public EntityModel<User> findById(@PathVariable Integer id) {

		Optional<User> user = userRepositoryService.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		EntityModel<User> entityModel = EntityModel.of(user.get());
		Link link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).findAll()).withRel("all-users");
		entityModel.add(link);
		return entityModel;
	}

	/*
	 * @PostMapping(path = "/user") public User saveUser(@RequestBody User user) {
	 * return userService.save(user); }
	 */

	@PostMapping(path = "/jpa/user")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User userSaved = userRepositoryService.save(user);
		// Created
		// user/id

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/jpa/user/{id}")
	public void deleteUserById(@PathVariable Integer id) {

		userRepositoryService.deleteById(id);
	}
	
	
	@GetMapping(path = "/jpa/user/{id}/post")
	public List<Post> retriveAllUser(@PathVariable Integer id) {

		Optional<User> user = userRepositoryService.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
         
		return user.get().getPost();
	}

}
