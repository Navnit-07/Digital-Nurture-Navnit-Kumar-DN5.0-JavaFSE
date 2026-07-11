package com.library.service;

import com.library.repository.BookRepository;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    // Default constructor
    public BookService() {
        System.out.println("BookService: default constructor called");
    }

    // Constructor injection
    public BookService(BookRepository bookRepository) {
        System.out.println("BookService: constructor injection called");
        this.bookRepository = bookRepository;
    }

    // Setter injection
    public void setBookRepository(BookRepository bookRepository) {
        System.out.println("BookService: setter injection called");
        this.bookRepository = bookRepository;
    }

    public List<String> findAllBooks() {
        return bookRepository.getAllBooks();
    }
}
