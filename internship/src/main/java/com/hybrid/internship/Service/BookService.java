package com.hybrid.internship.Service;

import com.hybrid.internship.Model.Book;
import com.hybrid.internship.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book insert(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public boolean delete(Long id) {
        Book bookForDeletion = bookRepository.findById(id).get();
        bookRepository.delete(bookForDeletion);
        return true;
    }
    public boolean update(long id, Book newBook) {
        Book bookForUpdate = bookRepository.findById(id).orElse(null);
        if (newBook.getAuthor() != null)
            bookForUpdate.setAuthor(newBook.getAuthor());
        if (newBook.getTitle() != null)
            bookForUpdate.setTitle(newBook.getTitle());
        bookRepository.save(bookForUpdate);
        return true;

    }
}
