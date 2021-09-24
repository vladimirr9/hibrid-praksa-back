package com.hybrid.internship.service;

import com.hybrid.internship.exception.EntityNotFoundException;
import com.hybrid.internship.model.User;
import com.hybrid.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User insert(User newUser) {
        Objects.requireNonNull(newUser);
        return repository.save(newUser);
    }

    public User findById(Long id) throws EntityNotFoundException {
        Objects.requireNonNull(id);
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No user with this ID found"));
    }

    public void delete(Long id) {
        User userForDeletion = findById(id);
        repository.delete(userForDeletion);
    }

    public User update(Long id, User newUser) {
        Objects.requireNonNull(newUser);
        User userForUpdate = new User(id,
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getFirstName(),
                newUser.getLastName());
        return repository.save(userForUpdate);
    }

}
