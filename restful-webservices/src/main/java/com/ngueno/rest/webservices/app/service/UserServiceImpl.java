package com.ngueno.rest.webservices.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngueno.rest.webservices.app.entities.User;
import com.ngueno.rest.webservices.app.exception.UserNotFoundException;
import com.ngueno.rest.webservices.app.repository.UserRepository;
import com.ngueno.rest.webservices.app.service.iface.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User find(Long id) {
		Optional<User> foundUser = userRepository.findById(id);

		if (!foundUser.isPresent()) {
			throw new UserNotFoundException(String.valueOf(id));
		}

		return foundUser.get();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

}
