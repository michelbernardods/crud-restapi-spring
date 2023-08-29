package com.exemple.restspring;

import com.exemple.restspring.model.Post;
import com.exemple.restspring.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Bean
    CommandLineRunner commandLineRunner(PostService service) {
		return args -> service.save(new Post("Hello World!","My first blog post!"));
	}

}
