package com.hybrid.internship.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;

    public Book(){}


    public Book(Long id, String title, String author) {
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

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

}
