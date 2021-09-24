package com.hybrid.internship.dto;

import com.hybrid.internship.model.BookCopy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private List<BookCopy> copies;

    public BookResponseDTO() {
    }

    public BookResponseDTO(Long id, String title, String author, List<BookCopy> copies) {
        this.id = Objects.requireNonNull(id);
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
        this.copies = Objects.isNull(copies) ? new ArrayList<>() : copies;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
