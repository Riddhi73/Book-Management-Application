package com.example.Book.Management.controller;

import com.example.Book.Management.dto.SignInResponseDto;
import com.example.Book.Management.dto.SignUpResponseDto;
import com.example.Book.Management.dto.user.SignInDto;
import com.example.Book.Management.dto.user.SignUpDto;
import com.example.Book.Management.model.Book;
import com.example.Book.Management.model.User;
import com.example.Book.Management.repository.UserRepo;
import com.example.Book.Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpDto signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto){
        return userService.signIn(signInDto);
    }
    @GetMapping
    public ResponseEntity<List<User>> getUser(){
        List<User> users = userRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
