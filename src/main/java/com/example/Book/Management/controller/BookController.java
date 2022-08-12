package com.example.Book.Management.controller;

import com.example.Book.Management.common.ApiResponse;
import com.example.Book.Management.dto.BookDto;
import com.example.Book.Management.model.Book;
import com.example.Book.Management.model.Category;
import com.example.Book.Management.repository.BookRepo;
import com.example.Book.Management.repository.CategoryRepo;
import com.example.Book.Management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    BookRepo bookRepo;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createBook(@RequestBody BookDto bookDto){
        Optional<Category> optionalCategory = categoryRepo.findById(bookDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Category Does Not Exist"), HttpStatus.BAD_REQUEST);
        }
        bookService.createBook(bookDto,optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true,"Book Has Been Added"), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Book>> getBook(){
        List<Book> books = bookRepo.findAll();
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping("/byId")
    public ResponseEntity<List<Book>> getBookById(@PathVariable("bookId") Integer bookId){
        List<Book> books = bookRepo.findByIdFromBook(bookId);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @PostMapping("/update{bookId}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable("bookId") Integer bookId, @RequestBody BookDto bookDto) throws Exception {
        Optional<Book> optionalBook = bookRepo.findById(bookId);
        if (!optionalBook.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Book Does Not Exist"), HttpStatus.BAD_REQUEST);
        }
        bookService.updateBook(bookDto,bookId);
        return new ResponseEntity<>(new ApiResponse(true,"Book Has Been Updated"), HttpStatus.OK);
    }
}
