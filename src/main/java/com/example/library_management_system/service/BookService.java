package com.example.library_management_system.service;

import com.example.library_management_system.dto.BookRequest;
import com.example.library_management_system.model.Book;

import java.util.List;

public interface BookService {
    Book addBook(BookRequest request);
    List<Book> getAllBooks();
}
