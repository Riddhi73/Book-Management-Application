package com.example.Book.Management.service;

import com.example.Book.Management.dto.SignInResponseDto;
import com.example.Book.Management.dto.SignUpResponseDto;
import com.example.Book.Management.dto.user.SignInDto;
import com.example.Book.Management.dto.user.SignUpDto;
import com.example.Book.Management.exceptions.AuthenticationFailException;
import com.example.Book.Management.exceptions.CustomException;
import com.example.Book.Management.model.AuthToken;
import com.example.Book.Management.model.User;
import com.example.Book.Management.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    AuthService authService;
    @Transactional
    public SignUpResponseDto signUp(SignUpDto signUpDto) {
        if(Objects.nonNull(userRepo.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("User Already Exists");
        }
        String encyptedPassword = signUpDto.getPassword();

        try{
            encyptedPassword = hashPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }
        User user = new User(signUpDto.getFirstName(),
                signUpDto.getLastName(),
                signUpDto.getEmail(),
                encyptedPassword,
                signUpDto.getDivision(),
                signUpDto.getZila(),
                signUpDto.getPostCode(),
                signUpDto.getImage());

        userRepo.save(user);
        final AuthToken authToken = new AuthToken(user);
        authService.saveConfirmationToken(authToken);
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto("success","user created successfully");
        return signUpResponseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        User user = userRepo.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)){
            throw new AuthenticationFailException("Please create an account");
        }

        try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException("wrong password");

            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        AuthToken authToken = authService.getToken(user);
        if (Objects.isNull(authToken)){
            throw new CustomException("Token is not present");
        }
        return new SignInResponseDto("success", authToken.getToken());
    }
}
