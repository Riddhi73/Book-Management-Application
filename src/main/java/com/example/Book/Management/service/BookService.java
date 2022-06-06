package com.example.Book.Management.service;

import com.example.Book.Management.dto.BookDto;
import com.example.Book.Management.model.Book;
import com.example.Book.Management.model.Category;
import com.example.Book.Management.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public void createBook(BookDto bookDto, Category category) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setDescription(bookDto.getDescription());
        book.setImageURL(bookDto.getImageURL());
        book.setCategory(category);
        book.setPrice(bookDto.getPrice());
        bookRepo.save(book);
    }

    public BookDto getBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        bookDto.setDescription(book.getDescription());
        bookDto.setImageURL(book.getImageURL());
        bookDto.setCategoryId(book.getCategory().getId());
        bookDto.setPrice(bookDto.getPrice());
        bookDto.setId(book.getId());
        return bookDto;
    }
    public List<BookDto> getAllBooks() {
        List<Book> allBooks = bookRepo.findAll();

        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book: allBooks){
            bookDtos.add(getBookDto(book));
        }
        return bookDtos;
    }

    public void updateBook(BookDto bookDto, Integer bookId) throws Exception {
        Optional<Book> optionalBook = bookRepo.findById(bookId);
        if (!optionalBook.isPresent()){
            throw new Exception("Book Not Present");
        }
        Book book = optionalBook.get();
        book.setName(bookDto.getName());
        book.setDescription(bookDto.getDescription());
        book.setImageURL(bookDto.getImageURL());
        book.setPrice(bookDto.getPrice());
        bookRepo.save(book);
    }
}
