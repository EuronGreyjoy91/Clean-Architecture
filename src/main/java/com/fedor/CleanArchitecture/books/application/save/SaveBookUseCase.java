package com.fedor.CleanArchitecture.books.application.save;

import com.fedor.CleanArchitecture.books.domain.Book;
import com.fedor.CleanArchitecture.books.domain.BookRepository;
import com.fedor.CleanArchitecture.books.domain.exception.RepeatedBookTitleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SaveBookUseCase implements SaveBook {

    private static final String SAVING_BOOK_LOG = "SaveBookUseCase - Saving Book: {}";
    private static final String SEARCHING_BOOK_BY_TITLE_LOG = "Searching the book with title: {}";
    private static final String REPEATED_BOOK_TITLE_LOG = "Book with repeated title, {}";

    private final BookRepository bookRepository;

    public SaveBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        log.info(SAVING_BOOK_LOG, book);

        log.info(SEARCHING_BOOK_BY_TITLE_LOG, book.getTitle());
        Optional<Book> savedBook = this.bookRepository.findByTitle(book.getTitle());
        if (savedBook.isPresent()) {
            log.error(REPEATED_BOOK_TITLE_LOG, book.getTitle());
            throw new RepeatedBookTitleException();
        }

        this.bookRepository.save(book);
    }
}
