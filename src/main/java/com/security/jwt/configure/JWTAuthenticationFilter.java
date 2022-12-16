package com.security.jwt.configure;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.jwt.helper.JWTHelper;
import com.security.jwt.service.CustomUserDetailsService;

// we have to create filter, which intersecpt the request before it access API's

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JWTHelper jwtHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
//		steps:
//		1. get the JWT Token
//		2. check weather it start from bearer
//		3. Validate user

		//check user has token, if yes then, extract the token 
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
//		checking null and Format
		if (requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			
//			get the token from header, after removing Bearer_ prefix 
			jwtToken = requestTokenHeader.substring(7);
			
			try {
//				get the username from token
				username = this.jwtHelper.extractUsername(jwtToken);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
//			The HttpServletRequest.getUserPrincipal() will return the result of 
//			SecurityContextHolder.getContext().getAuthentication(). This means it is an 
//			Authentication which is typically an instance of UsernamePasswordAuthenticationToken 
//			when using username and password based authentication
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
			
			if (username!= null && SecurityContextHolder.getContext().getAuthentication()==null) {
//				build token
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));
//				save the user in security context
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("user is not validated..");
				System.out.println("user has not send valid token..");
			}
		}
		
//		forward the process of filters
		filterChain.doFilter(request, response);

	}

}
