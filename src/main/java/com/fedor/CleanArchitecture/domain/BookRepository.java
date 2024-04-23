package com.fedor.CleanArchitecture.domain;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();

    void save(Book book);

    Optional<Book> findByTitle(String title);
}
