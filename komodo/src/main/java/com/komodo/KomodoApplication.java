package com.komodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// @EntityScan("<package with entities>")
@SpringBootApplication
public class KomodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KomodoApplication.class, args);
	}

}
