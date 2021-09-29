package com.hybrid.internship.controller;


import com.hybrid.internship.documentation.GlobalApiResponses;
import com.hybrid.internship.dto.BookCopyResponseDTO;
import com.hybrid.internship.dto.BookRequestDTO;
import com.hybrid.internship.dto.BookResponseDTO;
import com.hybrid.internship.model.Book;
import com.hybrid.internship.model.BookCopy;
import com.hybrid.internship.model.Role;
import com.hybrid.internship.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@GlobalApiResponses
@RequestMapping(value = "/api/v1/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Operation(summary = "Get all roles")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Roles found")})
    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        List<Role> found = roleService.findAll();
        return ResponseEntity.ok(found);
    }

    @Operation(summary = "Add a new role to the system")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Added new role to the system")})
    @PostMapping
    public ResponseEntity<Role> insert(@RequestBody @Valid Role role) {
        Role newRole = roleService.insert(role);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newRole.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(newRole);
    }
}