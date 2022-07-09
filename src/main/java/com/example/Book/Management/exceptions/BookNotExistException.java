package com.example.Book.Management.exceptions;

public class BookNotExistException extends IllegalArgumentException {
    public BookNotExistException(String s) {
        super(s);
    }
}
