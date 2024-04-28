package com.fedor.CleanArchitecture.books.infrastructure.mapper;

import com.fedor.CleanArchitecture.books.infrastructure.model.BookDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookDTORowMapper implements RowMapper<BookDTO> {

    @Override
    public BookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        String title = rs.getString("title");
        LocalDate publicationDate = rs.getDate("publication_date").toLocalDate();
        int stock = rs.getInt("stock");

        return new BookDTO(
                title,
                publicationDate,
                stock
        );
    }

}
