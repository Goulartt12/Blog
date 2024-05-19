package com.example.blog.controller;

import com.example.blog.domain.Login;
import com.example.blog.dto.LoginDto;
import com.example.blog.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping
    public List<Login> findAll(){
        return loginService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Login> findById(@PathVariable String id){
        return loginService.findById(id);
    }

    @PostMapping
    public Login newLogin(@RequestBody LoginDto loginDto){
        return loginService.newLogin(loginDto);
    }

    @PostMapping("/authentication")
    public ResponseEntity<String> authenticate(@RequestBody LoginDto loginDto){
        String username = loginDto.username();
        String password = loginDto.password();

        boolean authentication = loginService.Authentication(username, password);

        if(authentication){
            return ResponseEntity.ok("Logged!");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateLogin(@PathVariable String id, @RequestBody LoginDto loginDto){
        Login login = loginService.update(loginDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(login);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        loginService.delete(id);
    }
}
