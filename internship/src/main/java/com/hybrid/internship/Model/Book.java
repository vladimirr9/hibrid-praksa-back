package com.hybrid.internship.Model;

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

    public Book(String title, String author) {
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title);
    }

    public void setAuthor(String author) {
        this.author = Objects.requireNonNull(author);
    }
}
