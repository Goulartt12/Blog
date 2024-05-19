package com.example.blog.services;

import com.example.blog.domain.Login;
import com.example.blog.dto.LoginDto;
import com.example.blog.repositories.LoginRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private PasswordService passwordService;

    public List<Login> findAll(){
        return loginRepo.findAll();
    }

    public Optional<Login> findById(String id){
        return loginRepo.findById(id);
    }

    public Login newLogin(LoginDto loginDto){
        String bcrypt = passwordService.encryptPassword(loginDto.password());
        Login newlogin = new Login(loginDto);
        newlogin.setUsername(loginDto.username());
        newlogin.setEmail(loginDto.email());
        newlogin.setPassword(bcrypt);
        return loginRepo.save(newlogin);
    }

    public boolean Authentication(String username, String password){
        Optional<Login> user = loginRepo.findByUsername(username);
        if(user.isEmpty()){
            return false;
        }
        Login upser = user.get();
        return passwordService.checkPassword(password, upser.getPassword());
    }

    public Login update(LoginDto loginDto, String id){
        Optional<Login> user = loginRepo.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("User not found");
        }

        Login upUser = user.get();

        upUser.setUsername(loginDto.username());
        upUser.setEmail(loginDto.email());

        if(!loginDto.password().isEmpty()){
            String bcrypt = passwordService.encryptPassword(loginDto.password());
            upUser.setPassword(bcrypt);
        }

        return loginRepo.save(upUser);
    }

    public void delete(String id){
        Login user = loginRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found"));
        loginRepo.delete(user);
    }
}
