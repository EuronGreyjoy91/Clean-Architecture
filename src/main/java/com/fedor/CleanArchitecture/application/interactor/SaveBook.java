package com.fedor.CleanArchitecture.application.interactor;

import com.fedor.CleanArchitecture.domain.Book;

@FunctionalInterface
public interface SaveBook {
    void save(Book book);
}
