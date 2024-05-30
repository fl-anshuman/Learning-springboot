package org.CRUD.Learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningApplication {

	public static void main(String[] args) {
		MyController.home();
		SpringApplication.run(LearningApplication.class, args);
	}

}
