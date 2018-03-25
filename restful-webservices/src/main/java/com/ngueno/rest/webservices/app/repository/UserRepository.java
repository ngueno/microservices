package com.ngueno.rest.webservices.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngueno.rest.webservices.app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
