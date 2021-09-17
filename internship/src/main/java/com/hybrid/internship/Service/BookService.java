package com.hybrid.internship.Service;

import com.hybrid.internship.Model.Book;
import com.hybrid.internship.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return bookRepository.getById(id);
    }

    public boolean delete(String title) {
        Book bookForDeletion = bookRepository.findByTitle(title);
        if (bookForDeletion == null)
            return false;
        bookRepository.delete(bookRepository.findByTitle(title));
        return true;
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public boolean update(String title, Book newBook) {
        Book bookForUpdate = bookRepository.findByTitle(title);
        if (bookForUpdate == null)
            return false;

        bookForUpdate.setAuthor(newBook.getAuthor());
        bookForUpdate.setTitle(newBook.getTitle());
        bookRepository.save(bookForUpdate);
        return true;

    }
}
