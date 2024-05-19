package com.example.blog.repositories;

import com.example.blog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, String> {
    @NonNull
    Optional<Post> findById(@NonNull String id);

    @NonNull
    Optional<Post> findByTitle(@NonNull String title);

}
