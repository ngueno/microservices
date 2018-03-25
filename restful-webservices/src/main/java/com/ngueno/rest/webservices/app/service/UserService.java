package com.ngueno.rest.webservices.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngueno.rest.webservices.app.entities.User;
import com.ngueno.rest.webservices.app.exception.UserNotFoundException;
import com.ngueno.rest.webservices.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User find(Long id) {
		Optional<User> foundUser = userRepository.findById(id);

		if (!foundUser.isPresent()) {
			throw new UserNotFoundException(String.valueOf(id));
		}

		return foundUser.get();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
