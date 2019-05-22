package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.revature.dtos.AuthorDTO;
import com.revature.models.Book;
import com.revature.repositories.BookRepository;

@Service
public class BookService {

	BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book findById(int id) {
		Book book = bookRepository.findById(id).orElseThrow(
				() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
		
		// TODO Probably want to get the author data too
		System.out.println("Sending request for author");
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/authors/" + book.getAuthorId();
		AuthorDTO author = restTemplate.getForObject(url, AuthorDTO.class);
		book.setAuthor(author);		
		return book;
	}

	public Book create(Book book) {
		
		// Set up for the RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/authors/" + book.getAuthorId();
		
		// Reference will need to be accessed outside the try block
		ResponseEntity<AuthorDTO> response = null;
		try {
			// Send we the request
			response = restTemplate.getForEntity(url, AuthorDTO.class);
		} catch (HttpClientErrorException e) {
			// If author service has no author with that ID
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				// then we should tell our client that it was a bad request, need a valid authorId
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "No author with provided authorId");
			}
		}

		Book savedBook = this.bookRepository.save(book);
		savedBook.setAuthor(response.getBody());
		return savedBook;
	}

	public List<Book> getBooksByAuthor(int authorId) {
		return bookRepository.getBooksByAuthorId(authorId);
	}
	
	
}
