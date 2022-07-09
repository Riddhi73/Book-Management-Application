package com.example.Book.Management.repository;

import com.example.Book.Management.model.ShoppingCart;
import com.example.Book.Management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart,Integer> {
    List<ShoppingCart> findAllByUserOrderByCreatedDateDesc(User user);
}
