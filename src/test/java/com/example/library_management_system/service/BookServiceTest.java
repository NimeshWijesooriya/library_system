package com.example.library_management_system.service;

import com.example.library_management_system.dto.BookRequest;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        BookRequest request = new BookRequest();
        request.setIsbn("123");
        request.setTitle("Test Book");
        request.setAuthor("Author");

        Book savedBook = Book.builder().id(1L).isbn("123").title("Test Book").author("Author").isBorrowed(false).build();

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        Book result = bookService.addBook(request);

        assertNotNull(result);
        assertEquals("123", result.getIsbn());
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                Book.builder().id(1L).title("Book1").build(),
                Book.builder().id(2L).title("Book2").build()
        );

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
    }
}
