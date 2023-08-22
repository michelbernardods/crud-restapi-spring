package com.exemple.restspring.repository;

import com.exemple.restspring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
//    Optional<Post> findById(UUID id);
}
