package com.example.blog.services;

import com.example.blog.domain.Login;
import com.example.blog.domain.Post;
import com.example.blog.dto.PostDto;
import com.example.blog.repositories.LoginRepo;
import com.example.blog.repositories.PostRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private LoginRepo loginRepo;

    public List<Post> getall(){
        return postRepo.findAll();
    }

    public Optional<Post> findById(String id){
        return postRepo.findById(id);
    }

    public Optional<Post> findByTitle(String title){
        return postRepo.findByTitle(title);
    }

    public Post createPost(Post post, String user){
        Optional<Login> userr = loginRepo.findById(user);
        if(userr.isPresent()){
            post.setUser(userr.get());
            return postRepo.save(post);
        }
        else{
            throw new RuntimeException("User not found");
        }
    }

    public void deletePost(String id){
        postRepo.deleteById(id);
    }
}
