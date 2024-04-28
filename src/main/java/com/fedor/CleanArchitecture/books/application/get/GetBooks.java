package com.fedor.CleanArchitecture.books.application.get;

import com.fedor.CleanArchitecture.books.domain.Book;

import java.util.List;

@FunctionalInterface
public interface GetBooks {
    List<Book> getBooks();
}
