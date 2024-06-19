package com.leiten.loginservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/v1")
@Controller
public class LoginController {
	
	@GetMapping("/login")
	public static String login() {
		return "Logged In";
	}
		
}
