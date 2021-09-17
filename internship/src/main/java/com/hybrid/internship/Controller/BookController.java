package com.hybrid.internship.Controller;

import com.hybrid.internship.Model.Book;
import com.hybrid.internship.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/{id}")
    public @ResponseBody Book findOne(@PathVariable Long id) {
        return bookService.findById(id);
    }
    @GetMapping
    public @ResponseBody List<Book> findAll() {
        return bookService.findAll();
    }
    @PostMapping
    public @ResponseBody Book insert(@RequestBody Book book) {
        return bookService.insert(book);
    }
    @DeleteMapping(value = "/{id}")
    public @ResponseBody boolean delete(@PathVariable Long id) {
        return bookService.delete(id);
    }
    @PutMapping(value = "/{id}")
    public @ResponseBody boolean update(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

}
