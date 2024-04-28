package com.fedor.CleanArchitecture.books.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
    private String title;
    private LocalDate publicationDate;
    private int stock;

    public Book(String title, LocalDate publicationDate, int stock) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.stock = stock;
    }
}


