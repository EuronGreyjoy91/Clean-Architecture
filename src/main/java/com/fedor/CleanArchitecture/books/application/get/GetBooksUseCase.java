package com.fedor.CleanArchitecture.books.application.get;

import com.fedor.CleanArchitecture.books.domain.Book;
import com.fedor.CleanArchitecture.books.domain.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GetBooksUseCase implements GetBooks {

    private static final String GETTING_ALL_BOOKS_LOG = "GetBooksUseCase - Getting all books";

    private final BookRepository bookRepository;

    public GetBooksUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        log.info(GETTING_ALL_BOOKS_LOG);
        return this.bookRepository.findAll();
    }

}
