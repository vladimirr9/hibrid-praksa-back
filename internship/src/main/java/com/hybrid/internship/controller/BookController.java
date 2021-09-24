package com.hybrid.internship.controller;

import com.hybrid.internship.model.Book;
import com.hybrid.internship.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findOne(@PathVariable Long id, HttpServletRequest req) {
        Book found = bookService.findById(id);
        return ResponseEntity.ok().body(found);
    }
    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        List<Book> found = bookService.findAll();
        return ResponseEntity.ok().body(found);
    }
    @PostMapping
    public ResponseEntity<Book> insert(@RequestBody @Valid Book book) {
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
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book book) {
        Book updated = bookService.update(id, book);
        return ResponseEntity.ok().body(updated);

    }

}
