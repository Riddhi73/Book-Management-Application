package com.example.Book.Management.dto.shoppingcart;

import com.example.Book.Management.model.Book;
import com.example.Book.Management.model.ShoppingCart;

public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Book book;

    public CartItemDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CartItemDto(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        this.quantity = shoppingCart.getQuantity();
        this.setBook(shoppingCart.getBook());
    }
}
