package com.hybrid.internship.controller;

import com.hybrid.internship.model.Book;
import com.hybrid.internship.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/{id}")
    public Book findOne(@PathVariable Long id, HttpServletRequest req) {
        return bookService.findById(id);
    }
    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }
    @PostMapping
    public Book insert(@RequestBody Book book) {
        return bookService.insert(book);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
    @PutMapping(value = "/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

}
