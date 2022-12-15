package com.security.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	
	@GetMapping("/welcome")
	public String welcome() {
		String text = "this is private Page";
		text+= "this page is not allowed to unauthorized user";
		return text;
	}
	
	@GetMapping("/getUser")
	public String getUser() {
		return "user page";
	}

}
