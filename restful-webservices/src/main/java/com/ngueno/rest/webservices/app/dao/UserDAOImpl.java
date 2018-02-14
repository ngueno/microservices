package com.ngueno.rest.webservices.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ngueno.rest.webservices.app.dao.iface.IUserDAO;
import com.ngueno.rest.webservices.app.entities.User;

@Repository
public class UserDAOImpl implements IUserDAO {

	private List<User> users = new ArrayList<>();
	private static Long userCount = 3L;

	{
		users.add(new User(1L, "Norton", new Date()));
		users.add(new User(2L, "Xpto", new Date()));
		users.add(new User(3L, "ExampleName", new Date()));
	}

	@Override
	public List<User> findAll() {
		return users;
	}

	public User find(Long id) {
		return users.stream().filter(u -> id.equals(u.getId())).findAny().orElse(null);
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}

		users.add(user);

		return user;
	}

	@Override
	public User delete(Long id) {
		User user = find(id);

		if (user != null) {
			if (users.removeIf(u -> id.equals(u.getId()))) {
				return user;
			}
		}

		return null;
	}

}
