package com.example.accounts_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AccountsMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsMicroservicesApplication.class, args);
	}

}
