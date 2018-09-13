package com.ngueno.rest.webservices.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngueno.rest.webservices.app.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
