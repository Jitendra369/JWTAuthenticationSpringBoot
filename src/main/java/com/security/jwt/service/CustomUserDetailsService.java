package com.security.jwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


// this class is responsible for getting username and password
// we can load user information, either in Database or local-storage
@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if (username.equals("jittu")) {
//			this User class implement UserDetails Service 
			return new User("jittu", "android", new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User Not Found");
		}
		
	}

}
