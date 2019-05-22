package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.dtos.BookDTO;
import com.revature.feigns.BookFeign;
import com.revature.models.Author;
import com.revature.repositories.AuthorRepository;

@Service
public class AuthorService {
	
	AuthorRepository authorRepository;
	BookFeign bookFeign;
	
	@Autowired
	public AuthorService(AuthorRepository authorRepository, BookFeign bookFeign) {
		this.authorRepository = authorRepository;
		this.bookFeign = bookFeign;
	}

	public Author getById(int id) {
		return this.authorRepository.findById(id).orElseThrow(
				() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
	}

	public Author createAuthor(Author author) {
		return this.authorRepository.save(author);
	}

	public List<BookDTO> getAuthorBooks(int id) {
		// FeignClient
		// An interface that looks like an MVC class, but represents the
		// structure of some foreign controller
		// Note: We must explicitly enable FeignClients in configuration
		return this.bookFeign.getBooksByAuthorId(id);
	}
	
	

}
