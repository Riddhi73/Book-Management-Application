package com.example.Book.Management.service;

import com.example.Book.Management.model.AuthToken;
import com.example.Book.Management.model.User;
import com.example.Book.Management.repository.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    TokenRepo tokenRepo;
    public void saveConfirmationToken(AuthToken authToken) {
        tokenRepo.save(authToken);
    }

    public AuthToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }
}
