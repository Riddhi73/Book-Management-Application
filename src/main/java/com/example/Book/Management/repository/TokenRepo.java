package com.example.Book.Management.repository;

import com.example.Book.Management.model.AuthToken;
import com.example.Book.Management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthToken,Integer> {
    AuthToken findByUser(User user);

    AuthToken findByToken(String token);
}
