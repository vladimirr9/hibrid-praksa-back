package com.hybrid.internship.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class BookCopyRequestDTO {
    @NotBlank(message = "Code for a book copy cannot be blank")
    private String code;
    @NotNull(message = "Book ID to which this book refers to cannot be null")
    private Long bookId;

    public BookCopyRequestDTO() {}

    public String getCode() {
        return code;
    }

    public Long getBookId() {
        return bookId;
    }

    public BookCopyRequestDTO(String code, Long bookId) {
        this.code = Objects.requireNonNull(code);
        this.bookId = Objects.requireNonNull(bookId);
    }
}
