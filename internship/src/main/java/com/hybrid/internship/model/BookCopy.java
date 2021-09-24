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
import java.util.Objects;

@Entity
@Table(name = "book_copy")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "code", unique = true)
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