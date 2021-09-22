package com.hybrid.internship.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NaturalId
    private String code;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    public BookCopy() {}

    public BookCopy(Long id, String code, Book book) {
        this.id = Objects.requireNonNull(id);
        init(code, book);
    }

    public BookCopy(String code, Book book) {
        init(code,book);
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

    public void setBook(Book book) {
        this.book = Objects.requireNonNull(book);
    }
}
