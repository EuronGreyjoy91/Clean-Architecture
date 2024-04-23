package com.fedor.CleanArchitecture.infrastructure;

import com.fedor.CleanArchitecture.application.interactor.GetBooks;
import com.fedor.CleanArchitecture.application.interactor.SaveBook;
import com.fedor.CleanArchitecture.domain.Book;
import com.fedor.CleanArchitecture.infrastructure.model.BookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class BookController {

    private static final String GET_ALL_BOOKS_START_LOG = "Start GET - /api/v1/books";
    private static final String GET_ALL_BOOKS_END_LOG = "End GET - /api/v1/books - Response: {}";

    private static final String SAVE_BOOK_START_LOG = "Start POST - /api/v1/books";
    private static final String SAVE_BOOK_END_LOG = "End POST - /api/v1/books";

    private final GetBooks getBooksUseCase;
    private final SaveBook saveBookUseCase;

    public BookController(GetBooks getBooksUseCase, SaveBook saveBookUseCase) {
        this.getBooksUseCase = getBooksUseCase;
        this.saveBookUseCase = saveBookUseCase;
    }

    @GetMapping("/books")
    public List<BookResponse> listBooks() {
        log.info(GET_ALL_BOOKS_START_LOG);

        List<Book> books = this.getBooksUseCase.getBooks();
        List<BookResponse> bookResponse = books.stream().map(BookResponse::fromDomain).toList();

        log.info(GET_ALL_BOOKS_END_LOG, bookResponse);
        return bookResponse;
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBook(@RequestBody Book book) {
        log.info(SAVE_BOOK_START_LOG);

        this.saveBookUseCase.save(book);

        log.info(SAVE_BOOK_END_LOG);
    }
}
