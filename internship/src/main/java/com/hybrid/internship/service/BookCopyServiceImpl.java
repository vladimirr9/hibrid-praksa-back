package com.hybrid.internship.service;

import com.hybrid.internship.exception.CurrentlyRentedOutException;
import com.hybrid.internship.exception.EntityNotFoundException;
import com.hybrid.internship.model.BookCopy;
import com.hybrid.internship.model.User;
import com.hybrid.internship.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookCopyServiceImpl implements BookCopyService {
    @Autowired
    private BookCopyRepository repository;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;


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
        if (isCopyRentedOut(id)) {
            throw new CurrentlyRentedOutException();
        }
        repository.delete(bookForDeletion);
    }

    public BookCopy update(Long id, BookCopy bookCopy) {
        Objects.requireNonNull(bookCopy);
        if (isCopyRentedOut(id)) {
            throw new CurrentlyRentedOutException();
        }

        BookCopy bookCopyForUpdate = new BookCopy(id, bookCopy.getCode(), bookCopy.getBook(), bookCopy.getUser());
        return repository.save(bookCopyForUpdate);
    }

    public BookCopy rent(Long id, Long userId) {
        Objects.requireNonNull(userId);
        BookCopy bookCopy = findById(id);
        if (isCopyRentedOut(id)) {
            throw new CurrentlyRentedOutException();
        }
        User user = userService.findById(userId);
        bookCopy.setUser(user);
        repository.save(bookCopy);
        return bookCopy;
    }

    public BookCopy stopRent(Long id) {
        BookCopy bookCopy = findById(id);
        bookCopy.setUser(null);
        return repository.save(bookCopy);
    }

    private boolean isCopyRentedOut(Long id) {
        Objects.requireNonNull(id);
        Optional<BookCopy> bookCopyWrapper = repository.findById(id);
        if (bookCopyWrapper.isEmpty()) {
            return false;
        }
        BookCopy oldBookCopy = bookCopyWrapper.get();
        return oldBookCopy.getUser() != null;
    }

    public List<BookCopy> findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    public Long getUserIDForBookCopy(BookCopy bookCopy) {
        return bookCopy.getUser() == null ? null : bookCopy.getUser().getId();
    }

    public String getUserEmailForBookCopy(BookCopy bookCopy) {
        return bookCopy.getUser() == null ? null : bookCopy.getUser().getEmail();
    }


}
