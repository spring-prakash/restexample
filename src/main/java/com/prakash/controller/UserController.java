package com.prakash.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

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

import com.prakash.exception.UserNotFoundException;
import com.prakash.model.User;
import com.prakash.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	//Hal exposure http://localhost:8099/
	//Hal exposure http://localhost:8099/actuator

	@GetMapping(path = "/user")
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping(path = "/user/{id}")
	public EntityModel<User> findById(@PathVariable Integer id) {
		
		User user=userService.findById(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
		}
			
		EntityModel<User> entityModel = EntityModel.of(user);
        Link link= WebMvcLinkBuilder.linkTo(
                methodOn(this.getClass()).findAll()).withRel("all-users");
        entityModel.add(link);
        return entityModel;
	}

	/*
	 * @PostMapping(path = "/user") public User saveUser(@RequestBody User user) {
	 * return userService.save(user); }
	 */

	@PostMapping(path = "/user")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User userSaved = userService.save(user);
		// Created
		// user/id

		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	
	@DeleteMapping(path = "/user/{id}")
	public void deleteUserById(@PathVariable Integer id) {
		
		User user=userService.deleteUserById(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
		}
	}
}
