package com.example.Book.Management.dto.shoppingcart;

import java.util.List;

public class AllItemDto {
    private List<CartItemDto> cartItem;
    private double totalItemCost;

    public AllItemDto() {
    }

    public List<CartItemDto> getCartItemDtoList() {
        return cartItem;
    }

    public void setCartItemDtoList(List<CartItemDto> cartItemDtoList) {
        this.cartItem = cartItemDtoList;
    }

    public double getTotalItemCost() {
        return totalItemCost;
    }

    public void setTotalItemCost(double totalItemCost) {
        this.totalItemCost = totalItemCost;
    }
}
