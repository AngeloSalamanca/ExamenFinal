package com.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class ExamenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenApplication.class, args);
	}

}
