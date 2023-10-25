package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entity.User;

@RepositoryRestResource(path="users")
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
	public User findByUsername(String username);
}
