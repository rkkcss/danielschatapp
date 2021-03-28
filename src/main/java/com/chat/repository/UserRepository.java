package com.chat.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.chat.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findAll();
	
	User findByEmail(String email);
	
	User findByUsername(String username);
}
