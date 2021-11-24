package com.odds_and_ends.backendv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Backendv1Application {

	public static void main(String[] args) {
		SpringApplication.run(Backendv1Application.class, args);
	}

}
