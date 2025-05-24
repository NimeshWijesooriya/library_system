package com.example.library_management_system.service;

public interface BorrowService {
    void borrowBook(Long borrowerId, Long bookId);
    void returnBook(Long borrowerId, Long bookId);
}
