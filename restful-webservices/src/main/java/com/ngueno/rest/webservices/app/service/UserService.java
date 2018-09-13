package com.ngueno.rest.webservices.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngueno.rest.webservices.app.entities.Post;
import com.ngueno.rest.webservices.app.entities.User;
import com.ngueno.rest.webservices.app.exception.UserNotFoundException;
import com.ngueno.rest.webservices.app.repository.PostRepository;
import com.ngueno.rest.webservices.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User find(Long id) {
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException(String.valueOf(id));
		}

		return userOptional.get();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public List<Post> retrieveAllPosts(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException(String.valueOf(id));
		}
		
		return userOptional.get().getPosts();
	}

	public Post createPost(Long id, Post post) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException(String.valueOf(id));
		}

		post.setUser(userOptional.get());
		
		postRepository.save(post);
		
		return post;
	}
}
