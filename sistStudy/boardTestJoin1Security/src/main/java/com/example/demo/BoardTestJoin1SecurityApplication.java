package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BoardTestJoin1SecurityApplication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("pWE 만들어짐!");
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BoardTestJoin1SecurityApplication.class, args);
	}

}
