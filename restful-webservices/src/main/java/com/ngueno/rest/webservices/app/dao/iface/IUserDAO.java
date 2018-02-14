package com.ngueno.rest.webservices.app.dao.iface;

import java.util.List;

import com.ngueno.rest.webservices.app.entities.User;

public interface IUserDAO {

	List<User> findAll();

	User find(Long id);

	User save(User user);

	User delete(Long id);
}
