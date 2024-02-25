package com.example.COGIP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.COGIP")
public class CogipApplication {

	public static void main(String[] args) {
		SpringApplication.run(CogipApplication.class, args);
	}

}
