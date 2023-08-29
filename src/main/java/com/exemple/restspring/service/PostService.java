package com.exemple.restspring.service;

import com.exemple.restspring.model.Post;
import com.exemple.restspring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> findAllPosts(){
        return repository.findAll();
    }

    public void insertPost(Post post) {
         repository.save(post);
    }

    public Optional<Post> findById(UUID id) {
        return repository.findById(id);
    }

    public void delete(UUID post) {
        repository.deleteById(post);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Object save(Post postModel) {
        return repository.save(postModel);
    }
}
