package com.example.blog.controller;

import com.example.blog.domain.Post;
import com.example.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getall(){
        return postService.getall();
    }

    @GetMapping("/{id}")
    public Optional<Post> findById(@PathVariable String id){
        return postService.findById(id);
    }

    @GetMapping("/{title}")
    public Optional<Post> findByTitle(@PathVariable String title){
        return postService.findByTitle(title);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post, String user_id){
        Post post1 = new Post();
        post1.setTitle(post.getTitle());
        post1.setContent(post.getContent());
        Post createdPost = postService.createPost(post1, post.getId());
        return ResponseEntity.ok(createdPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id){
        Optional<Post> post = postService.findById(id);
        if(post.isPresent()){
            postService.deletePost(id);
        }
    }
}
