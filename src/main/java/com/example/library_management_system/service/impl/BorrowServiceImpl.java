package com.example.library_management_system.service.impl;

import com.example.library_management_system.exception.ResourceNotFoundException;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.BorrowRecord;
import com.example.library_management_system.model.Borrower;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.BorrowRecordRepository;
import com.example.library_management_system.repository.BorrowerRepository;
import com.example.library_management_system.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    @Override
    public void borrowBook(Long borrowerId, Long bookId) {
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (book.isBorrowed()) {
            throw new IllegalStateException("Book is already borrowed");
        }

        book.setBorrowed(true);
        bookRepository.save(book);

        BorrowRecord record = BorrowRecord.builder()
                .borrower(borrower)
                .book(book)
                .borrowDate(LocalDate.now())
                .build();
        borrowRecordRepository.save(record);
    }

    @Override
    public void returnBook(Long borrowerId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setBorrowed(false);
        bookRepository.save(book);

        BorrowRecord record = borrowRecordRepository.findAll().stream()
                .filter(r -> r.getBook().getId().equals(bookId) && r.getReturnDate() == null)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Borrow record not found"));

        record.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(record);
    }
}
