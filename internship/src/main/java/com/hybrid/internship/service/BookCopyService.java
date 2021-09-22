package com.hybrid.internship.service;

import com.hybrid.internship.model.BookCopy;

import java.util.List;

public interface BookCopyService {
    List<BookCopy> findAll();

    BookCopy insert(BookCopy bookCopy);

    BookCopy findById(Long id);

    void delete(Long id);

    BookCopy update(Long id, BookCopy bookCopy);
}
