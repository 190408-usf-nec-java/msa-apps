package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul acts as a reverse proxy.
 * 
 * A proxy routes a request through another network location to hide the
 * location of the requester. A proxy protects the client.
 * 
 * A reverse proxy passes requests through another network location to hide the
 * target of the request. A reverse proxy protects the server.
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
