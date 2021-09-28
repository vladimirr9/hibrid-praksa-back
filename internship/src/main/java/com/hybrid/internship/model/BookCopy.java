package com.hybrid.internship.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "book_copy")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Code for book copy cannot be blank")
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;


    public BookCopy() {
    }

    public BookCopy(Long id, String code, Book book, User user) {
        this.user = user;
        this.id = Objects.requireNonNull(id);
        init(code, book);
    }

    public BookCopy(String code, Book book) {
        init(code, book);
    }

    private void init(String code, Book book) {
        this.code = Objects.requireNonNull(code);
        this.book = Objects.requireNonNull(book);
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = Objects.requireNonNull(book);
    }
}
