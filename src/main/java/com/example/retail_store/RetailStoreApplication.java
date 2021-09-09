package com.example.retail_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
public class RetailStoreApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {

		SpringApplication.run(RetailStoreApplication.class, args);
	}

}
