package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// @EnableEurekaClient tells the application that it should be a client
// to Eureka, and thus it should attempt to register itself with Eureka

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
