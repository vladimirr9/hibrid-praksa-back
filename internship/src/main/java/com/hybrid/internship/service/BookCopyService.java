package com.hybrid.internship.service;

import com.hybrid.internship.exception.EntityNotFoundException;
import com.hybrid.internship.model.BookCopy;
import com.hybrid.internship.model.User;

import java.util.List;

public interface BookCopyService {

    /**
     * @return - All book copies within the system
     */
    List<BookCopy> findAll();

    /**
     * @param bookCopy - Book copy to be added
     * @return - Newly added book copy
     */
    BookCopy insert(BookCopy bookCopy);

    /**
     * @param id - ID of the book copy that we're looking for, cannot be null
     * @return - Book copy with the given id
     * @throws EntityNotFoundException if no book with given id is found
     */
    BookCopy findById(Long id);

    /**
     * @param id - ID of the book copy that we're deleting, cannot be null
     */
    void delete(Long id);

    /**
     * @param id       - ID of the book copy that we're updating, cannot be null
     * @param bookCopy - New book copy to replace the one with given ID, fields cannot be null
     * @return - Newly replaced book copy
     */
    BookCopy update(Long id, BookCopy bookCopy);

    /**
     * @param id     - ID of the book copy that is to be rented out
     * @param userId - ID of the user who the book copy is to be rented out to
     * @return - Newly rented out book copy
     */
    BookCopy rent(Long id, Long userId);

    /**
     * @param id - ID of the book coyp that is to stop being rented out
     */
    BookCopy stopRent(Long id);

    /**
     *
     * @param id - ID of the user whose copies we want to find
     * @return - All copies currently rented out by the user
     */
    List<BookCopy> findByUserId(Long id);
}
