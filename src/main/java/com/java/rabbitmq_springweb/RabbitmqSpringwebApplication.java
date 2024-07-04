package com.java.rabbitmq_springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitmqSpringwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqSpringwebApplication.class, args);
	}

}
