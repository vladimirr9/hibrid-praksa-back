package com.hybrid.internship.controller;

import com.hybrid.internship.model.Book;
import com.hybrid.internship.model.User;
import com.hybrid.internship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id, HttpServletRequest req) {
        User found = userService.findById(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> found = userService.findAll();
        return new ResponseEntity<>(found, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user) {
        User newUser = userService.insert(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(newUser);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.update(id, user);
        return new ResponseEntity<>(updated, HttpStatus.OK);

    }




}
