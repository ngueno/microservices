package com.ngueno.rest.webservices.app.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ngueno.rest.webservices.app.entities.Post;
import com.ngueno.rest.webservices.app.entities.User;
import com.ngueno.rest.webservices.app.service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable Long id) {
		User user = userService.find(id);

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		Resource<User> resource = new Resource<User>(user);
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.delete(id);
	}

	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable Long id) {
		return userService.retrieveAllPosts(id);
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable Long id, @RequestBody Post post) {
		Post savedPost = userService.createPost(id, post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
