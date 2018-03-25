package com.ngueno.rest.webservices.app.service.iface;

import java.util.List;

import com.ngueno.rest.webservices.app.entities.User;

public interface IUserService {

	List<User> findAll();

	User find(Long id);

	User save(User user);

	void delete(User user);
}
