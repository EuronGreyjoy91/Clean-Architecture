package com.fedor.CleanArchitecture.application.interactor;

import com.fedor.CleanArchitecture.domain.Book;

import java.util.List;

@FunctionalInterface
public interface GetBooks {
    List<Book> getBooks();
}
