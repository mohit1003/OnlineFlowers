package com.online.flowers.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online.flowers.model.CustomerModel;
import com.online.flowers.repo.CustomerRepo;

@Service
public class CustomUserAuthService implements UserDetailsService {
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		CustomerModel user = customerRepo.findByEmail(email);
		
		return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}

}