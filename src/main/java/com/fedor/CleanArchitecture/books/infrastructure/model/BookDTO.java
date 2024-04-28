package com.fedor.CleanArchitecture.books.infrastructure.model;

import com.fedor.CleanArchitecture.books.domain.Book;
import lombok.Value;

import java.time.LocalDate;

@Value
public class BookDTO {
    String title;
    LocalDate publicationDate;
    int stock;

    public static Book toDomain(BookDTO bookDTO) {
        return new Book(
                bookDTO.getTitle(),
                bookDTO.getPublicationDate(),
                bookDTO.getStock()
        );
    }
}
