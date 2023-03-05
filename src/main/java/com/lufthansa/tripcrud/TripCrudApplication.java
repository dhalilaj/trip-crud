package com.lufthansa.tripcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TripCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(TripCrudApplication.class, args);
	}
}
