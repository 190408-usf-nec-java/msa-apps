package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.dtos.BookDTO;
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
	
	@GetMapping("{id:[\\d]+}/books")
	public List<BookDTO> getAuthorBooks(@PathVariable int id) {
		return authorService.getAuthorBooks(id);
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Author createAuthor(@RequestBody Author author) {
		return authorService.createAuthor(author);
	}
	
	@DeleteMapping("{id}")
	public Author deleteAuthor(@PathVariable int id) {
		return authorService.deleteAuthor(id);
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handleException(HttpClientErrorException e) {
		return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
	}
	
}
