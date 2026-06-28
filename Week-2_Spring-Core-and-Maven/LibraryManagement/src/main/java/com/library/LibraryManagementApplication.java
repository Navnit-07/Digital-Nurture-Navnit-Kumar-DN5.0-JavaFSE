package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

// Exercise 5 & 7: Load Spring IoC container and test DI
public class LibraryManagementApplication {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");

        List<String> books = bookService.findAllBooks();

        System.out.println("Books in library:");
        for (String book : books) {
            System.out.println("- " + book);
        }
    }
}
