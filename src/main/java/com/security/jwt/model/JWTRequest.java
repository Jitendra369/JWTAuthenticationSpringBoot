package com.security.jwt.model;


// this model class use to send username and password in API
public class JWTRequest {
	
	private String username;
	private String password;
	public JWTRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "JWTRequest [username=" + username + ", password=" + password + "]";
	}
	
	

}
