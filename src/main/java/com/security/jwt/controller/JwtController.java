 package com.security.jwt.controller;

import java.nio.channels.NonReadableChannelException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.jwt.helper.JWTHelper;
import com.security.jwt.model.JWTRequest;
import com.security.jwt.model.JWTResponse;
import com.security.jwt.service.CustomUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTHelper jwtHelper;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

//	token mapping....
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);
//		return ResponseEntity.ok();
		try {
//			get username and password form user/client
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credintials ");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credintials");
		}
		
		
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
//		get the token from JWT class
		String token = this.jwtHelper.generateToken(userDetails);
		System.out.println("JWT Token :" + token);
		
//		we have to send json like {"token":"token-value"}
		return ResponseEntity.ok(new JWTResponse(token));
	}
}
