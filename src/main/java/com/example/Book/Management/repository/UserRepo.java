package com.example.Book.Management.repository;

import com.example.Book.Management.model.Book;
import com.example.Book.Management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<User> findAll();
    User findByEmail(String email);
}
