package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dao.UserRepository;
import com.entity.User;

@Service
public class UserServiceImp implements UserService {

	

	    @Autowired
	    private UserRepository userRepository;

	    @Override
	    public Optional<User> findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }

	    @Override
	    public void registerUser(User user) {
	        userRepository.save(user);
	    }
	}
