package com.hybrid.internship.controller;

import com.hybrid.internship.documentation.GlobalApiResponses;
import com.hybrid.internship.dto.BookRequestDTO;
import com.hybrid.internship.dto.BookResponseDTO;
import com.hybrid.internship.dto.mapper.BookMapper;
import com.hybrid.internship.model.Book;
import com.hybrid.internship.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books")
@GlobalApiResponses
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book found")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<BookResponseDTO> findOne(@PathVariable Long id, HttpServletRequest req) {
        Book found = bookService.findById(id);
        BookResponseDTO responseDTO = bookMapper.toResponseDTO(found);
        return ResponseEntity.ok(responseDTO);
    }
    @Operation(summary = "Get all books in the system")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Books found")})
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findAll() {
        List<Book> found = bookService.findAll();
        List<BookResponseDTO> foundDTO = new ArrayList<>();
        for (var book : found) {
            foundDTO.add(bookMapper.toResponseDTO(book));
        }
        return ResponseEntity.ok(foundDTO);
    }
    @Operation(summary = "Add a new book to the system")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Added new book to the system")})
    @PostMapping
    public ResponseEntity<BookResponseDTO> insert(@RequestBody @Valid BookRequestDTO bookRequestDTO) {
        Book book = bookMapper.fromDTO(bookRequestDTO);
        Book newBook = bookService.insert(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBook.getId())
                .toUri();
        BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(newBook);
        return ResponseEntity.created(location)
                .body(bookResponseDTO);
    }
    @Operation(summary = "Remove a book based on its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Book removed")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Replace a book based on its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book replaced")})
    @PutMapping(value = "/{id}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable Long id, @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        Book book = bookMapper.fromDTO(bookRequestDTO);
        Book updated = bookService.update(id, book);
        BookResponseDTO bookResponseDTO = bookMapper.toResponseDTO(updated);
        return ResponseEntity.ok(bookResponseDTO);

    }

}
