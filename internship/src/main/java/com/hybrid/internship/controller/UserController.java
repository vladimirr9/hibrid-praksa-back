package com.hybrid.internship.controller;

import com.hybrid.internship.dto.UserRequestDTO;
import com.hybrid.internship.dto.UserResponseDTO;
import com.hybrid.internship.dto.mapper.UserMapper;
import com.hybrid.internship.model.User;
import com.hybrid.internship.service.UserService;
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
@RequestMapping(value = "/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findOne(@PathVariable Long id, HttpServletRequest req) {
        User found = userService.findById(id);
        UserResponseDTO userResponseDTO = userMapper.toResponseDTO(found);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<User> found = userService.findAll();
        ArrayList<UserResponseDTO> foundDTO = new ArrayList<>();
        for (var user : found) {
            foundDTO.add(userMapper.toResponseDTO(user));
        }
        return ResponseEntity.ok(foundDTO);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        User user = userMapper.fromDTO(userRequestDTO);
        User newUser = userService.insert(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        UserResponseDTO newUserDTO = userMapper.toResponseDTO(newUser);
        return ResponseEntity.created(location)
                .body(newUserDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        User user = userMapper.fromDTO(userRequestDTO);
        User updated = userService.update(id, user);
        UserResponseDTO userResponseDTO = userMapper.toResponseDTO(updated);
        return ResponseEntity.ok(userResponseDTO);

    }


}
