package com.revature.commands;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.dtos.AuthorDTO;
import com.revature.models.Book;

@Component
public class AuthorCommand {

	// fallbackMethod is a method that can be called in the case that there is an exception
	// while processing the HystrixCommand (in this case getBookAuthor)
	// Note: The fallback method must have the same signature except for the method name
	@HystrixCommand(fallbackMethod="getBookAuthorFallback")
	public void getBookAuthor(Book book) {
		System.out.println("Sending request for author");
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/authors/" + book.getAuthorId();
		AuthorDTO author = restTemplate.getForObject(url, AuthorDTO.class);
		book.setAuthor(author);		
	}
	
	// This method will be called if there is a problem with the HystrixCommand
	public void getBookAuthorFallback(Book book) {
		book.setAuthor(null);
	}
}
