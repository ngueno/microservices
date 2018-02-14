package com.ngueno.rest.webservices.app.entities;

import java.util.Date;
import java.util.List;

public class User {

	private Long id;
	private Date birtDate;
	private String name;

	private List<Post> posts;

	public User() {

	}

	public User(Long id, String name, Date birtDate) {
		super();
		this.id = id;
		this.birtDate = birtDate;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBirtDate() {
		return birtDate;
	}

	public void setBirtDate(Date birtDate) {
		this.birtDate = birtDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
