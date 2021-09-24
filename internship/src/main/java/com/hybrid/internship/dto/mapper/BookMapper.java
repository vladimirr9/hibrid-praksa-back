package com.hybrid.internship.dto.mapper;

import com.hybrid.internship.dto.BookRequestDTO;
import com.hybrid.internship.dto.BookResponseDTO;
import com.hybrid.internship.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book fromDTO(BookRequestDTO bookRequestDTO) {
        return new Book(bookRequestDTO.getTitle(), bookRequestDTO.getAuthor());
    }

    public BookResponseDTO toResponseDTO(Book book) {
        return new BookResponseDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getCopies());
    }
}
