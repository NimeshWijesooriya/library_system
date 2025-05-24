package com.example.library_management_system.service;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.BorrowRecord;
import com.example.library_management_system.model.Borrower;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.BorrowRecordRepository;
import com.example.library_management_system.repository.BorrowerRepository;
import com.example.library_management_system.service.impl.BorrowServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BorrowServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @Mock
    private BorrowRecordRepository borrowRecordRepository;

    @InjectMocks
    private BorrowServiceImpl borrowService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBorrowBookSuccessfully() {
        Borrower borrower = Borrower.builder().id(1L).name("John").email("john@example.com").build();
        Book book = Book.builder().id(1L).isbn("123").title("Title").author("Author").isBorrowed(false).build();

        when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        borrowService.borrowBook(1L, 1L);

        verify(bookRepository).save(any(Book.class));
        verify(borrowRecordRepository).save(any(BorrowRecord.class));
    }

    @Test
    void testReturnBookSuccessfully() {
        Book book = Book.builder().id(1L).isbn("123").title("Title").author("Author").isBorrowed(true).build();
        BorrowRecord record = BorrowRecord.builder().id(1L).book(book).borrowDate(LocalDate.now()).build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(borrowRecordRepository.findByBookIdAndReturnDateIsNull(1L)).thenReturn(Optional.<Book>of(record.getBook()));

        borrowService.returnBook(1L, 1L);

        verify(bookRepository).save(any(Book.class));
        verify(borrowRecordRepository).save(any(BorrowRecord.class));
    }
}

