package com.hybrid.internship.service;

import com.hybrid.internship.exception.BookNotFoundException;
import com.hybrid.internship.model.Book;
import com.hybrid.internship.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository repository;

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book insert(Book newBook) {
        Objects.requireNonNull(newBook);
        return repository.save(newBook);
    }

    public Book findById(Long id) throws BookNotFoundException {
        Objects.requireNonNull(id);
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException("No book with this ID found"));
    }

    public void delete(Long id) {
        Book bookForDeletion = findById(id);
        repository.delete(bookForDeletion);
    }

    public Book update(Long id, Book newBook) {
        Objects.requireNonNull(newBook);
        Book existingBook = findById(id);
        Book bookForUpdate = new Book(existingBook.getId(), newBook.getTitle(), newBook.getAuthor());
        return repository.save(bookForUpdate);
    }
}
