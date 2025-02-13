package com.thanproject.library_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thanproject.library_management_system.model.Users;
import com.thanproject.library_management_system.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private UserRepo userRepo;
	
	
	@Autowired
	public MyUserDetailsService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users findByUsername = userRepo.findByUsername(username);
		if(findByUsername == null) {
			throw new UsernameNotFoundException("Username: "+ username+" not found!");
		}
		return User.builder()
				.username(findByUsername.getUsername())
				.password(findByUsername.getPassword())
				.roles(findByUsername.getUserRole().toString())
				.build();
	}

}
