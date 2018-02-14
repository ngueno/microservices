package com.ngueno.rest.webservices.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngueno.rest.webservices.app.dao.iface.IUserDAO;
import com.ngueno.rest.webservices.app.entities.User;
import com.ngueno.rest.webservices.app.exception.UserNotFoundException;
import com.ngueno.rest.webservices.app.service.iface.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public User find(Long id) {
		User user = userDAO.find(id);

		if (user == null) {
			throw new UserNotFoundException(String.valueOf(id));
		}

		return user;
	}

	@Override
	public User save(User user) {
		return userDAO.save(user);
	}

	@Override
	public void delete(Long id) {
		User deletedUser = userDAO.delete(id);

		if (deletedUser == null) {
			throw new UserNotFoundException(String.valueOf(id));
		}
	}

}
