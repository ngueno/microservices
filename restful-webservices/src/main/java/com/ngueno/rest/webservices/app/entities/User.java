package com.ngueno.rest.webservices.app.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Details about the user")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, message = "Name should have at least 2 characters")
	@ApiModelProperty(notes = "Name should have at least 2 characters")
	private String name;

	@Past
	@ApiModelProperty(notes = "Birth date should in the past")
	private Date birtDate;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	public User() {

	}

	public User(Long id) {
		super();
		this.id = id;
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
