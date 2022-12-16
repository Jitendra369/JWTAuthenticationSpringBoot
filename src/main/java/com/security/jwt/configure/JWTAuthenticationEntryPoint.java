package com.security.jwt.configure;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//This class will extend Spring's AuthenticationEntryPoint class and override its method commence.
//It rejects every unauthenticated request and send error code 401

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint{

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.sendError(401, "UnAuthorized");
	}

}
