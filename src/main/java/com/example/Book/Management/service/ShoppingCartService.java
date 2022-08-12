package com.example.Book.Management.service;

import com.example.Book.Management.dto.shoppingcart.AddToCartDto;
import com.example.Book.Management.dto.shoppingcart.AllItemDto;
import com.example.Book.Management.dto.shoppingcart.CartItemDto;
import com.example.Book.Management.exceptions.CustomException;
import com.example.Book.Management.model.Book;
import com.example.Book.Management.model.ShoppingCart;
import com.example.Book.Management.model.User;
import com.example.Book.Management.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {
    @Autowired
    BookService bookService;
    @Autowired
    ShoppingCartRepo shoppingCartRepo;
    public void addToCart(AddToCartDto addToCartDto, User user) {
        Book book = bookService.findById(addToCartDto.getBookId());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setBook(book);
        shoppingCart.setUser(user);
        shoppingCart.setQuantity(addToCartDto.getQuantity());
        shoppingCart.setCreatedDate(new Date());
        shoppingCartRepo.save(shoppingCart);
    }

    public AllItemDto listCartItem(User user) {
       final List<ShoppingCart> shoppingCarts = shoppingCartRepo.findAllByUserOrderByCreatedDateDesc(user);
       List<CartItemDto> cartItem = new ArrayList<>();
       double totalCost = 0;
       for (ShoppingCart shoppingCart: shoppingCarts){
           CartItemDto cartItemDto = new CartItemDto(shoppingCart);
           totalCost += cartItemDto.getQuantity() * shoppingCart.getBook().getPrice();
           cartItem.add(cartItemDto);
       }
       AllItemDto allItemDto = new AllItemDto();
       allItemDto.setTotalItemCost(totalCost);
       allItemDto.setCartItemDtoList(cartItem);
       return allItemDto;
    }

    public void delete(Integer cartItemId, User user) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepo.findById(cartItemId);

        if (optionalShoppingCart.isEmpty()){
            throw new CustomException("cart item is invalid: " + cartItemId);
        }
        ShoppingCart shoppingCart = optionalShoppingCart.get();
        if (shoppingCart.getUser() != user){
            throw new CustomException("cart item doesn't belong to user: "+ cartItemId);
        }
        shoppingCartRepo.delete(shoppingCart);
    }
}
