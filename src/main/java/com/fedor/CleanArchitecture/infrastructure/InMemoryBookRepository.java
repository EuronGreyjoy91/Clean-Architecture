package com.fedor.CleanArchitecture.infrastructure;

import com.fedor.CleanArchitecture.domain.Book;
import com.fedor.CleanArchitecture.domain.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Primary
@Repository
public class InMemoryBookRepository implements BookRepository {

    private static final String GET_ALL_BOOKS_LOG = "Finding all books with InMemoryRepository";
    private static final String SAVING_BOOK_LOG = "Saving book with InMemoryRepository";

    private final List<Book> books;

    public InMemoryBookRepository() {
        Book bookOne = new Book("The lord of the rings", LocalDate.now(), 5);
        Book bookTwo = new Book("3 body problem", LocalDate.now(), 10);

        this.books = new ArrayList<>();
        this.books.add(bookOne);
        this.books.add(bookTwo);
    }

    @Override
    public List<Book> findAll() {
        log.info(GET_ALL_BOOKS_LOG);
        return books;
    }

    @Override
    public void save(Book book) {
        log.info(SAVING_BOOK_LOG);
        this.books.add(book);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return this.books
                .stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst();
    }
}
