package com.spring.security.backend.controller;

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
	
	@GetMapping("/private/second")
	public String getSecond() {
		return "Hello first!";
	}
}