package com.hybrid.internship.controller;


import com.hybrid.internship.model.BookCopy;
import com.hybrid.internship.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books/copies/")
public class BookCopyController {
    @Autowired
    private BookCopyService bookCopyService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<BookCopy> findOne(@PathVariable Long id, HttpServletRequest req) {
        BookCopy found = bookCopyService.findById(id);
        return ResponseEntity.ok().body(found);
    }
    @GetMapping
    public ResponseEntity<List<BookCopy>> findAll() {
        List<BookCopy> found = bookCopyService.findAll();
        return ResponseEntity.ok().body(found);
    }
    @PostMapping
    public ResponseEntity<BookCopy> insert(@RequestBody BookCopy bookCopy) {
        BookCopy newBookCopy = bookCopyService.insert(bookCopy);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookCopy.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(newBookCopy);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        bookCopyService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<BookCopy> update(@PathVariable Long id, @RequestBody BookCopy bookCopy) {
        BookCopy updated = bookCopyService.update(id, bookCopy);
        return ResponseEntity.ok().body(updated);

    }


}
