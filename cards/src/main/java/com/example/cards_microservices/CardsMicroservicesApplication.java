package com.example.cards_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CardsMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsMicroservicesApplication.class, args);
	}

}
