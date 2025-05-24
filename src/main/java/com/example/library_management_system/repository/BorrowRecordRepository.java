package com.example.library_management_system.repository;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Optional<Book> findByBookIdAndReturnDateIsNull(Long bookId);
}
