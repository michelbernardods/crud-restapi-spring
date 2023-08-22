package com.exemple.restspring.controller;

import com.exemple.restspring.model.Post;
import com.exemple.restspring.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {



    @Autowired
    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/findAll")
    public List<Post> findAllPosts() {
        return repository.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getOnePost(@PathVariable UUID id){
        Optional<Post> post = repository.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(post.get());
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertPost(@RequestBody Post post) {
        repository.save(post);
        return ResponseEntity.status(HttpStatus.OK).body("Product created successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteOnePost(@PathVariable UUID id) {
        Optional<Post> productO = repository.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        repository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllPosts() {
        List<Post> post  = repository.findAll();
        if(!post.isEmpty()) {
            repository.deleteAll();
            return ResponseEntity.status(HttpStatus.OK).body("Products deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateOnePost(@PathVariable UUID id,
                                                @RequestBody @Valid Post post) {
        Optional<Post> productO = repository.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var postModel = productO.get();
        BeanUtils.copyProperties(post, postModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(postModel));
    }



}
