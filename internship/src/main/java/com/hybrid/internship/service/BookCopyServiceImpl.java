package com.hybrid.internship.service;

import com.hybrid.internship.exception.EntityNotFoundException;
import com.hybrid.internship.model.Book;
import com.hybrid.internship.model.BookCopy;
import com.hybrid.internship.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookCopyServiceImpl implements BookCopyService{
    @Autowired
    private BookCopyRepository repository;
    @Autowired
    private BookService bookService;


    public List<BookCopy> findAll() {
        return repository.findAll();
    }

    public BookCopy insert(BookCopy bookCopy) {
        Objects.requireNonNull(bookCopy);
        BookCopy saved = repository.save(bookCopy);
        saved.setBook(bookService.findById(bookCopy.getBook().getId()));
        return saved;
    }

    public BookCopy findById(Long id) {
        Objects.requireNonNull(id);
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No book copy with this ID found"));
    }

    public void delete(Long id) {
        BookCopy bookForDeletion = findById(id);
        repository.delete(bookForDeletion);
    }

    public BookCopy update(Long id, BookCopy bookCopy) {
        Objects.requireNonNull(bookCopy);
        BookCopy bookCopyForUpdate = new BookCopy(id, bookCopy.getCode(), bookCopy.getBook());
        return repository.save(bookCopyForUpdate);
    }


}
