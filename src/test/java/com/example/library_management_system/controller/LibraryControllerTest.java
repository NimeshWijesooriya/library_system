package com.example.library_management_system.controller;

import com.example.library_management_system.dto.BookRequest;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.BorrowerRepository;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.service.BorrowService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LibraryController.class)
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BorrowService borrowService;

    @MockBean
    private BorrowerRepository borrowerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddBook() throws Exception {
        BookRequest request = new BookRequest();
        request.setIsbn("123");
        request.setTitle("Book");
        request.setAuthor("Author");

        Book book = Book.builder().id(1L).isbn("123").title("Book").author("Author").isBorrowed(false).build();
        Mockito.when(bookService.addBook(any(BookRequest.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Book"))
                .andDo(print());
    }

    @Test
    void testGetAllBooks() throws Exception {
        Mockito.when(bookService.getAllBooks()).thenReturn(Arrays.asList(
                Book.builder().id(1L).title("Book1").build(),
                Book.builder().id(2L).title("Book2").build()
        ));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andDo(print());
    }
}