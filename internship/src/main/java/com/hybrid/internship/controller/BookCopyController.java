package com.hybrid.internship.controller;


import com.hybrid.internship.documentation.GlobalApiResponses;
import com.hybrid.internship.dto.BookCopyRequestDTO;
import com.hybrid.internship.dto.BookCopyResponseDTO;
import com.hybrid.internship.dto.RentRequestDTO;
import com.hybrid.internship.dto.RentResponseDTO;
import com.hybrid.internship.dto.mapper.BookCopyMapper;
import com.hybrid.internship.dto.mapper.RentMapper;
import com.hybrid.internship.model.BookCopy;
import com.hybrid.internship.model.User;
import com.hybrid.internship.service.BookCopyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@GlobalApiResponses
@RequestMapping(value = "/api/v1/book-copies/")
public class BookCopyController {
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private BookCopyMapper bookCopyMapper;
    @Autowired
    private RentMapper rentMapper;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Operation(summary = "Get a book copy by its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book copy found")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<BookCopyResponseDTO> findOne(@PathVariable Long id) {
        BookCopy found = bookCopyService.findById(id);
        BookCopyResponseDTO responseDTO = bookCopyMapper.toResponseDTO(found);
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Operation(summary = "Get all book copies")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book found")})
    @GetMapping
    public ResponseEntity<List<BookCopyResponseDTO>> findAll() {
        List<BookCopy> found = bookCopyService.findAll();
        ArrayList<BookCopyResponseDTO> foundDTO = new ArrayList<>();
        for (var bookCopy : found) {
            foundDTO.add(bookCopyMapper.toResponseDTO(bookCopy));
        }
        return ResponseEntity.ok(foundDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create a new book copy")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New book copy created")})
    @PostMapping
    public ResponseEntity<BookCopyResponseDTO> insert(@RequestBody @Valid BookCopyRequestDTO bookCopyRequestDTO) {
        BookCopy bookCopy = bookCopyMapper.fromDTO(bookCopyRequestDTO);
        BookCopy newBookCopy = bookCopyService.insert(bookCopy);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBookCopy.getId())
                .toUri();
        BookCopyResponseDTO responseDTO = bookCopyMapper.toResponseDTO(newBookCopy);
        return ResponseEntity.created(location)
                .body(responseDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Remove a book copy based on its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Book copy removed")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        bookCopyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Replaced a book copy based on its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book copy replaced")})
    @PutMapping(value = "/{id}")
    public ResponseEntity<BookCopyResponseDTO> update(@PathVariable Long id, @RequestBody @Valid BookCopyRequestDTO bookCopyRequestDTO) {
        BookCopy bookCopy = bookCopyMapper.fromDTO(bookCopyRequestDTO);
        BookCopy updated = bookCopyService.update(id, bookCopy);
        BookCopyResponseDTO responseDTO = bookCopyMapper.toResponseDTO(updated);
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Operation(summary = "Find user who is renting the book copy with given id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Renting info displayed")})
    @GetMapping(value = "/{id}/rent")
    public ResponseEntity<RentResponseDTO> findUserWhoRents(@PathVariable Long id) {

        BookCopy bookCopy = bookCopyService.findById(id);
        Long userId = bookCopyService.getUserIDForBookCopy(bookCopy);
        String userEmail = bookCopyService.getUserEmailForBookCopy(bookCopy);
        RentResponseDTO rentResponseDTO = rentMapper.toResponseDTO(bookCopy, userId, userEmail);
        return ResponseEntity.ok(rentResponseDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Operation(summary = "Rent out a book copy to a user")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book copy rented out")})
    @PostMapping(value = "/{id}/rent")
    public ResponseEntity<RentResponseDTO> rentBookCopy(@PathVariable Long id, @RequestBody @Valid RentRequestDTO rentRequestDTO) {
        BookCopy bookCopy = bookCopyService.rent(id, rentRequestDTO.getUserId());
        RentResponseDTO rentResponseDTO = rentMapper.toResponseDTO(bookCopy);
        return ResponseEntity.ok(rentResponseDTO);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Operation(summary = "Stop renting out a book copy to a user")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book copy withdrawn from renting")})
    @PutMapping(value = "/{id}/rent")
    public ResponseEntity<RentResponseDTO> stopRent(@PathVariable Long id) {
        BookCopy bookCopy = bookCopyService.stopRent(id);
        RentResponseDTO rentResponseDTO = rentMapper.toResponseDTO(bookCopy, null, null);
        return ResponseEntity.ok(rentResponseDTO);
    }
}
