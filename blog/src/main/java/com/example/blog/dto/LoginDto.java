package com.example.blog.dto;

import com.example.blog.domain.Post;

import java.util.List;

public record LoginDto(String username, String email, String password, List<Post> posts) {
}
