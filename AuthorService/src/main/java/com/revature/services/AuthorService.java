package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.models.Author;
import com.revature.repositories.AuthorRepository;

@Service
public class AuthorService {
	
	AuthorRepository authorRepository;

	@Autowired
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	public Author getById(int id) {
		return this.authorRepository.findById(id).orElseThrow(
				() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
	}

	public Author createAuthor(Author author) {
		return this.authorRepository.save(author);
	}
	
	

}
