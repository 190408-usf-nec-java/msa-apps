package com.revature.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.dtos.AuthorDTO;
import com.revature.services.BookService;

@Component
public class BookListener {

	BookService bookService;

	@Autowired
	public BookListener(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	@RabbitListener( queues = "book-service-delete")
	public void handleDeleteMessage(final AuthorDTO author) {
		System.out.println("Recieving message to delete books with author id: " + author.getId());
		bookService.deleteBooksByAuthor(author);
	}
}
