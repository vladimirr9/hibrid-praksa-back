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

    @GetMapping(value = "/{title}")
    public @ResponseBody Book findOne(@PathVariable String title) {
        return bookService.findByTitle(title);
    }
    @GetMapping
    public @ResponseBody List<Book> findAll() {
        return bookService.findAll();
    }
    @PostMapping
    public @ResponseBody Book insert(@RequestBody Book book) {
        return bookService.insert(book);
    }
    @DeleteMapping(value = "/{title}")
    public @ResponseBody boolean delete(@PathVariable String title) {
        return bookService.delete(title);
    }
    @PutMapping(value = "/{title}")
    public @ResponseBody boolean update(@PathVariable String title, @RequestBody Book book) {
        return bookService.update(title, book);
    }

}
