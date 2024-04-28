package com.fedor.CleanArchitecture.books.application.save;

import com.fedor.CleanArchitecture.books.domain.Book;

@FunctionalInterface
public interface SaveBook {
    void save(Book book);
}
