package com.hybrid.internship.dto;

import javax.validation.constraints.NotBlank;

public class BookRequestDTO {
    @NotBlank(message = "Book must have a title")
    private String title;
    @NotBlank(message = "Book must have an author")
    private String author;

    public BookRequestDTO() {
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
