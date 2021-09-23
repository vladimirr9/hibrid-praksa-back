package com.hybrid.internship.service;

import com.hybrid.internship.exception.EntityNotFoundException;
import com.hybrid.internship.model.BookCopy;

import java.util.List;

public interface BookCopyService {

    /**
     *
     * @return - All book copies within the system
     */
    List<BookCopy> findAll();

    /**
     *
     * @param bookCopy - Book copy to be added
     * @return - Newly added book copy
     */
    BookCopy insert(BookCopy bookCopy);

    /**
     *
     * @param id - ID of the book copy that we're looking for, cannot be null
     * @return - Book copy with the given id
     * @throws EntityNotFoundException if no book with given id is found
     */
    BookCopy findById(Long id);

    /**
     *
     * @param id - ID of the book copy that we're deleting, cannot be null
     */
    void delete(Long id);

    /**
     *
     * @param id - ID of the book copy that we're updating, cannot be null
     * @param bookCopy - New book copy to replace the one with given ID, fields cannot be null
     * @return - Newly replaced book copy
     */
    BookCopy update(Long id, BookCopy bookCopy);
}
