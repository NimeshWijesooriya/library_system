package com.example.library_management_system.controller;

import com.example.library_management_system.dto.BookRequest;
import com.example.library_management_system.dto.BorrowerRequest;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Borrower;
import com.example.library_management_system.repository.BorrowerRepository;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LibraryController {

    private final BookService bookService;
    private final BorrowService borrowService;
    private final BorrowerRepository borrowerRepository;

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.addBook(request));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/borrowers")
    public ResponseEntity<Borrower> registerBorrower(@RequestBody BorrowerRequest request) {
        Borrower borrower = Borrower.builder().name(request.getName()).email(request.getEmail()).build();
        return ResponseEntity.ok(borrowerRepository.save(borrower));
    }

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam Long borrowerId, @RequestParam Long bookId) {
        borrowService.borrowBook(borrowerId, bookId);
        return ResponseEntity.ok("Book borrowed successfully.");
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam Long borrowerId, @RequestParam Long bookId) {
        borrowService.returnBook(borrowerId, bookId);
        return ResponseEntity.ok("Book returned successfully.");
    }
}