package com.example.Book.Management.repository;

import com.example.Book.Management.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT * FROM book", nativeQuery = true)
    List<Book> findAll();
    @Query(value = "SELECT b.name FROM book b WHERE b.id", nativeQuery = true)
    List<Book> findByIdFromBook(Integer bookId);
//    @Query(value = "SELECT * FROM tutorials t WHERE t.title LIKE %?1%", nativeQuery = true)
//    List<Book> findByTitleLike(String title);
//    @Query(value = "SELECT * FROM tutorials t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
//    List<Book> findByTitleLikeCaseInsensitive(String title);
}
