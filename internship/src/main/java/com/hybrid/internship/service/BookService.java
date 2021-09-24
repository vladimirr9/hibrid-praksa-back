package com.hybrid.internship.service;

import com.hybrid.internship.exception.EntityNotFoundException;
import com.hybrid.internship.model.Book;

import java.util.List;


public interface BookService {
    /**
     *
     * @return - All books within the system
     */
    List<Book> findAll();

    /**
     *
     * @param newBook - Book to be added
     * @return - Newly added book
     */
    Book insert(Book newBook);

    /**
     *
     * @param id - ID of the book that we're looking for, cannot be null
     * @return - Book with the given id
     * @throws EntityNotFoundException if no book with given id is found
     */
    Book findById(Long id) throws EntityNotFoundException;

    /**
     *
     * @param id - ID of the book that we're deleting, cannot be null
     */
    void delete(Long id);

    /**
     *
     * @param id - ID of the book that we're updating, cannot be null
     * @param newBook - New book to replace the one with given ID, fields cannot be null
     * @return - Newly replaced book
     */
    Book update(Long id, Book newBook);
}
