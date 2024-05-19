package com.example.blog.dto;

import com.example.blog.domain.Login;

import java.time.LocalDateTime;

public record PostDto(String title, String content, LocalDateTime createdAt, Login user) {
}
