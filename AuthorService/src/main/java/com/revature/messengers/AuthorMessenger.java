package com.revature.messengers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.models.Author;

@Component
public class AuthorMessenger {

	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public AuthorMessenger(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendDeleteMessage(Author author) {
		System.out.println("Sending delete message with id: " + author.getId());
		author.setBirthdate(null);
		rabbitTemplate.convertAndSend("", "book-service-delete", author);
	}

}
