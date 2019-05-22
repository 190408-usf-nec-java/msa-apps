package com.revature.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AuthorController {
	@GetMapping("")
	public String sayHello() {
		return "{\"message\": \"hello Pete\"}";
	}
}
