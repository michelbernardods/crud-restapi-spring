package com.exemple.restspring.controller;

import com.exemple.restspring.model.Post;
import com.exemple.restspring.repository.PostRepository;
import com.exemple.restspring.service.PostService;
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
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public List<Post> findAllPosts() {
        return service.findAllPosts();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getOnePost(@PathVariable UUID id){
        Optional<Post> post = service.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(post.get());
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertPost(@RequestBody Post post) {
        service.insertPost(post);
        return ResponseEntity.status(HttpStatus.OK).body("Product created successfully.");
    }
//
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteOnePost(@PathVariable UUID id) {
        Optional<Post> productO = service.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        service.delete(productO.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllPosts() {
        List<Post> post  = service.findAllPosts();
        if(!post.isEmpty()) {
            service.deleteAll();
            return ResponseEntity.status(HttpStatus.OK).body("Products deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateOnePost(@PathVariable UUID id,
                                                @RequestBody @Valid Post post) {
        Optional<Post> productO = service.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var postModel = productO.get();
        BeanUtils.copyProperties(post, postModel);
        return ResponseEntity.status(HttpStatus.OK).body(service.save(postModel));
    }

}
