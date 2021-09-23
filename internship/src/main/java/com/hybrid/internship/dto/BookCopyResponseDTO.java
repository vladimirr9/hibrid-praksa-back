package com.hybrid.internship.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class BookCopyResponseDTO {
    @NotBlank(message = "ID for book copy cannot be blank")
    private Long id;
    @NotBlank(message = "Code for a book copy cannot be blank")
    private String code;
    @NotBlank(message = "Book ID to which this book refers to cannot be blank")
    private Long bookId;

    public BookCopyResponseDTO() {}

    public Long getId() {
        return id;
    }
    public String getCode() {
        return code;
    }
    public Long getBookId() {
        return bookId;
    }

    public BookCopyResponseDTO(Long id, String code, Long bookId) {
        this.id = Objects.requireNonNull(id);
        this.code = Objects.requireNonNull(code);
        this.bookId = Objects.requireNonNull(bookId);
    }
}
