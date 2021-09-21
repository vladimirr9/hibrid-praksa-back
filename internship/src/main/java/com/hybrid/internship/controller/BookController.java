package com.hybrid.internship.controller;

import com.hybrid.internship.model.Book;
import com.hybrid.internship.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findOne(@PathVariable Long id, HttpServletRequest req) {
        Book book = bookService.findById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        List<Book> found = bookService.findAll();
        return new ResponseEntity<>(found, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Book> insert(@RequestBody Book book) {
        Book newBook = bookService.insert(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBook.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(newBook);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        Book updated = bookService.update(id, book);
        return new ResponseEntity<>(updated, HttpStatus.OK);

    }

}
