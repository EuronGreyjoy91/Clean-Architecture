package com.fedor.CleanArchitecture.books.infrastructure;

import com.fedor.CleanArchitecture.books.domain.Book;
import com.fedor.CleanArchitecture.books.domain.BookRepository;
import com.fedor.CleanArchitecture.books.infrastructure.mapper.BookDTORowMapper;
import com.fedor.CleanArchitecture.books.infrastructure.model.BookDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class PostgreSQLBookRepository implements BookRepository {

    private static final String FINDING_BOOKS_LOG = "Finding books with PostgreSQLBookRepository";
    private static final String SAVING_BOOK_LOG = "Saving book with PostgreSQLBookRepository";
    private static final String BOOK_NOT_FOUND_BY_TITLE_LOG = "Book with title {} not found";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PostgreSQLBookRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Book> findAll() {
        log.info(FINDING_BOOKS_LOG);
        List<BookDTO> bookDTOList = namedParameterJdbcTemplate.query(
                "SELECT * FROM books",
                new BookDTORowMapper()
        );

        return bookDTOList.stream().map(BookDTO::toDomain).toList();
    }

    @Override
    public void save(Book book) {
        log.info(SAVING_BOOK_LOG);
        namedParameterJdbcTemplate.update(
                "INSERT INTO books (title, publication_date, stock) VALUES (:title, :publication_date, :stock)",
                Map.of(
                        "title", book.getTitle(),
                        "publication_date", book.getPublicationDate(),
                        "stock", book.getStock()
                )
        );
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        try {
            BookDTO bookDTO = namedParameterJdbcTemplate.queryForObject(
                    "SELECT * FROM books WHERE title = :title",
                    Map.of("title", title),
                    new BookDTORowMapper()
            );

            if (bookDTO == null)
                return Optional.empty();

            return Optional.of(BookDTO.toDomain(bookDTO));
        } catch (EmptyResultDataAccessException e) {
            log.warn(BOOK_NOT_FOUND_BY_TITLE_LOG, title);
            return Optional.empty();
        }
    }
}
