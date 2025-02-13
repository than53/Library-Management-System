package com.thanproject.library_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanproject.library_management_system.model.Users;
import com.thanproject.library_management_system.repository.UserRepo;

@RestController
@RequestMapping("/user")
public class UserController {

	
//	Checking purposes if Configuration is working
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/addUser")
	public void addUser(@RequestBody Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}
}
