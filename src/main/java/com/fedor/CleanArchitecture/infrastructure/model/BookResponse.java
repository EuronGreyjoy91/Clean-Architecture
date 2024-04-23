package com.fedor.CleanArchitecture.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fedor.CleanArchitecture.domain.Book;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookResponse {
    String title;

    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate publicationDate;

    int stock;

    public static BookResponse fromDomain(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .publicationDate(book.getPublicationDate())
                .stock(book.getStock())
                .build();
    }
}
