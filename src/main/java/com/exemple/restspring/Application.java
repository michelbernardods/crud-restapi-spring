package com.exemple.restspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//	@Bean
//	CommandLineRunner commandLineRunner(PostRepository repository) {
//		return args -> repository.save(new Post("Hello World!","My first blog post!"));
//	}

}
