package com.example.Book.Management.service;

import com.example.Book.Management.dto.BookDto;
import com.example.Book.Management.model.User;
import com.example.Book.Management.model.WishList;
import com.example.Book.Management.repository.WishListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    WishListRepo wishListRepo;
    @Autowired
    BookService bookService;

    public void createWishList(WishList wishList) {
        wishListRepo.save(wishList);
    }

    public List<BookDto> getWishListForUser(User user) {
        final List<WishList> wishLists = wishListRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<BookDto> bookDtos = new ArrayList<>();
        for (WishList wishList: wishLists){
            bookDtos.add(bookService.getBookDto(wishList.getBook()));
        }
        return  bookDtos;
    }
}
