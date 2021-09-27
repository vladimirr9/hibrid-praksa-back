package com.hybrid.internship.dto;

import java.util.Objects;

public class RentResponseDTO {

    private Long id;
    private String code;
    private Long bookId;
    private Long userId;
    private String email;

    public RentResponseDTO() {
    }

    public RentResponseDTO(Long id, String code, Long bookId, Long userId, String email) {
        this.id = Objects.requireNonNull(id);
        this.code = Objects.requireNonNull(code);
        this.bookId = Objects.requireNonNull(bookId);
        this.userId = userId;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}
