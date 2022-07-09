package com.example.Book.Management.controller;

import com.example.Book.Management.common.ApiResponse;
import com.example.Book.Management.dto.shoppingcart.AddToCartDto;
import com.example.Book.Management.dto.shoppingcart.AllItemDto;
import com.example.Book.Management.model.Book;
import com.example.Book.Management.model.User;
import com.example.Book.Management.service.AuthService;
import com.example.Book.Management.service.BookService;
import com.example.Book.Management.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private AuthService authService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token){
        authService.authenticate(token);
        User user = authService.getUser(token);

        shoppingCartService.addToCart(addToCartDto,user);
        return new ResponseEntity<>(new ApiResponse(true,"Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/allitem")
    public ResponseEntity<AllItemDto> getCartItems(@RequestParam("token") String token){
        authService.authenticate(token);
        User user = authService.getUser(token);
        AllItemDto allItemDto = shoppingCartService.listCartItem(user);
        return new ResponseEntity<>(allItemDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer cartItemId,
                                                      @RequestParam("token") String token){
        authService.authenticate(token);
        User user = authService.getUser(token);
        shoppingCartService.delete(cartItemId,user);
        return new ResponseEntity<>(new ApiResponse(true,"Item has been removed"), HttpStatus.OK);
    }
}
