package com.localhost.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorFormLogin {

	@GetMapping("/login")
	public String login () {	
		return "login";
	}
	
	@GetMapping("/admin")
	public String admin () {	
		
		return "admin";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
}
