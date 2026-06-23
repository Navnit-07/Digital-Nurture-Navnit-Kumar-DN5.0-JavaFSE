package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class LibraryManagementApplication {

    public static void main(String[] args) {

        // Load Spring application context from XML
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get BookService bean from Spring container
        BookService bookService = (BookService) context.getBean("bookService");

        // Test dependency injection
        List<String> books = bookService.findAllBooks();

        System.out.println("Books in library:");
        for (String book : books) {
            System.out.println("- " + book);
        }
    }
}
