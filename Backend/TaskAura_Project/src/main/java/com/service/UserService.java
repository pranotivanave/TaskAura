package com.service;

import java.util.Optional;

import com.entity.User;

public interface UserService {

	
	  Optional<User> findByUsername(String username);
	    void registerUser(User user);
	}
