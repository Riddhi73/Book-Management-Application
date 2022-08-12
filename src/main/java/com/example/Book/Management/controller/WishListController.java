package com.example.Book.Management.controller;

import com.example.Book.Management.common.ApiResponse;
import com.example.Book.Management.dto.BookDto;
import com.example.Book.Management.model.Book;
import com.example.Book.Management.model.User;
import com.example.Book.Management.model.WishList;
import com.example.Book.Management.service.AuthService;
import com.example.Book.Management.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;

    @Autowired
    AuthService authService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Book book,
                                                     @RequestParam("token") String token){
        authService.authenticate(token);
        User user = authService.getUser(token);
        WishList wishList = new WishList(user,book);
        wishListService.createWishList(wishList);
        ApiResponse apiResponse = new ApiResponse(true,"Added to wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<BookDto>> getWishList(@PathVariable("token") String token){
        authService.authenticate(token);
        User user = authService.getUser(token);
        List<BookDto> bookDtos = wishListService.getWishListForUser(user);
        return new ResponseEntity<>(bookDtos,HttpStatus.OK);
    }
}
