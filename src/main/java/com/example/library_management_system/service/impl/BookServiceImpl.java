package com.example.library_management_system.service.impl;

import com.example.library_management_system.dto.BookRequest;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book addBook(BookRequest request) {
        // Validate ISBN title and author consistency here if necessary
        return bookRepository.save(Book.builder()
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .author(request.getAuthor())
                .isBorrowed(false)
                .build());
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
