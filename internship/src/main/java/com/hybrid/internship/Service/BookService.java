package com.hybrid.internship.Service;

import com.hybrid.internship.Exceptions.BookNotFoundException;
import com.hybrid.internship.Model.Book;
import com.hybrid.internship.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book insert(Book book) {
        return repository.save(book);
    }

    public Book findById(Long id) throws BookNotFoundException {
        Objects.requireNonNull(id);
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException("No book with this ID found"));
    }
    public void delete(Long id) {
        Book bookForDeletion = findById(id);
        repository.delete(bookForDeletion);
    }
    public Book update(long id, Book newBook) {
        Book bookForUpdate = findById(id);
        if (newBook.getAuthor() != null)
            bookForUpdate.setAuthor(newBook.getAuthor());
        if (newBook.getTitle() != null)
            bookForUpdate.setTitle(newBook.getTitle());
        return repository.save(bookForUpdate);
    }
}
