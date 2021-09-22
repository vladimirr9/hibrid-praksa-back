package com.hybrid.internship.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String title;
    private String author;

    @JsonManagedReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookCopy> copies;

    public Book(){
    }


    public Book(Long id, String title, String author) {
        Objects.requireNonNull(id);
        this.id = id;
        init(title, author);
    }

    private void init(String title, String author) {
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
    }

    public Book(String title, String author) {
        init(title, author);
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

    public List<BookCopy> getCopies() {
        return copies;
    }
}
