package com.revature.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class MQConfig {
	public static final String EXCHANGE_NAME = "book.deletion";
	public static final String QUEUE_NAME = "book-service-delete";
	public static final String ROUTING_KEY = "book-service-delete-route";
	
	@Bean
	public DirectExchange appExchange() {
		DirectExchange exchange = new DirectExchange(EXCHANGE_NAME);
		return exchange;
	}
	
	@Bean
	public Queue appQueueSpecific() {
		return new Queue(QUEUE_NAME);
	}
	
	@Bean
	public Binding declareBindingSpecific() {
		return BindingBuilder.bind(appQueueSpecific())
				.to(appExchange())
				.with(ROUTING_KEY);
	}
	
	@Bean
	public RabbitTemplate rabbitTEmplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
