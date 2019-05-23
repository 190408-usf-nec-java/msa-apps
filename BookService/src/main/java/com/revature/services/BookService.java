package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.revature.commands.AuthorCommand;
import com.revature.dtos.AuthorDTO;
import com.revature.models.Book;
import com.revature.repositories.BookRepository;

@Service
public class BookService {

	BookRepository bookRepository;
	AuthorCommand authorCommand;
	
	@Autowired
	public BookService(BookRepository bookRepository, AuthorCommand authorCommand) {
		this.bookRepository = bookRepository;
		this.authorCommand = authorCommand;
	}

	public Book findById(int id) {
		Book book = bookRepository.findById(id).orElseThrow(
				() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
		
		// TODO Probably want to get the author data too
		authorCommand.getBookAuthor(book);
		
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
	
	public void deleteBooksByAuthor(AuthorDTO author) {
		this.bookRepository.deleteById(author.getId());
	}
	
}
