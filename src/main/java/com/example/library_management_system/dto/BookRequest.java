package com.example.library_management_system.dto;


import lombok.Data;

@Data
public class BookRequest {
    private String isbn;
    private String title;
    private String author;
}