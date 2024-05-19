package com.example.blog.repositories;

import com.example.blog.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<Login, String> {

    @NonNull
    Optional<Login> findByUsername(@NonNull String username);

    @NonNull
    Optional<Login> findById(@NonNull String id);
}
