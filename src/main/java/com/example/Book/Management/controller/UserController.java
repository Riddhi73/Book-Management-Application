package com.example.Book.Management.controller;

import com.example.Book.Management.dto.SignInResponseDto;
import com.example.Book.Management.dto.SignUpResponseDto;
import com.example.Book.Management.dto.user.SignInDto;
import com.example.Book.Management.dto.user.SignUpDto;
import com.example.Book.Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpDto signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto){
        return userService.signIn(signInDto);
    }
}
