package com.hybrid.internship.controller;


import com.hybrid.internship.dto.BookCopyRequestDTO;
import com.hybrid.internship.dto.BookCopyResponseDTO;
import com.hybrid.internship.dto.mapper.BookCopyMapper;
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
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books/copies/")
public class BookCopyController {
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private BookCopyMapper modelMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookCopyResponseDTO> findOne(@PathVariable Long id, HttpServletRequest req) {
        BookCopy found = bookCopyService.findById(id);
        BookCopyResponseDTO responseDTO = modelMapper.toResponseDTO(found);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookCopyResponseDTO>> findAll() {
        List<BookCopy> found = bookCopyService.findAll();
        ArrayList<BookCopyResponseDTO> foundDTO = new ArrayList<>();
        for (var bookCopy : found) {
            foundDTO.add(modelMapper.toResponseDTO(bookCopy));
        }
        return ResponseEntity.ok(foundDTO);
    }

    @PostMapping
    public ResponseEntity<BookCopyResponseDTO> insert(@RequestBody @Valid BookCopyRequestDTO bookCopyRequestDTO) {
        BookCopy bookCopy = modelMapper.fromDTO(bookCopyRequestDTO);
        BookCopy newBookCopy = bookCopyService.insert(bookCopy);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBookCopy.getId())
                .toUri();
        BookCopyResponseDTO responseDTO = modelMapper.toResponseDTO(newBookCopy);
        return ResponseEntity.created(location)
                .body(responseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        bookCopyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookCopyResponseDTO> update(@PathVariable Long id, @RequestBody @Valid BookCopyRequestDTO bookCopyRequestDTO) {
        BookCopy bookCopy = modelMapper.fromDTO(bookCopyRequestDTO);
        BookCopy updated = bookCopyService.update(id, bookCopy);
        BookCopyResponseDTO responseDTO = modelMapper.toResponseDTO(updated);
        return ResponseEntity.ok(responseDTO);
    }
}
