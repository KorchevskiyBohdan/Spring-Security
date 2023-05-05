package com.spring.security.backend.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping
public class TestController {

	@GetMapping("/public/first")
	public String getFirst() {
		return "Hello first!";
	}
	
	@GetMapping("/private/first")
	public String getSecond() {
		return "Hello first!";
	}
	
	@GetMapping("/first")
	public String getThird() {
		return "Hello first!";
	}
}