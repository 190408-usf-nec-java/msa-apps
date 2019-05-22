package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Author;
import com.revature.services.AuthorService;

@RestController
@RequestMapping("")
public class AuthorController {
	
	AuthorService authorService;
	
	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@GetMapping("{id}")
	public Author getAuthorById(@PathVariable int id) {
		return authorService.getById(id);
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Author createAuthor(@RequestBody Author author) {
		return authorService.createAuthor(author);
	}
}
