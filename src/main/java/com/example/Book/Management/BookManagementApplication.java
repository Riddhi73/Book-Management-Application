package com.example.Book.Management;

import com.example.Book.Management.model.Book;
import com.example.Book.Management.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookManagementApplication {
@Autowired
	BookRepo bookRepo;
	public static void main(String[] args) {
		SpringApplication.run(BookManagementApplication.class, args);
	}

	public void run(String... args) throws Exception {
		bookRepo.findAll();
	}
	private void show(List<Book> book) {
		book.forEach(System.out::println);
	}
}
