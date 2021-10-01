package com.hybrid.internship.service;

import com.hybrid.internship.exception.EntityNotFoundException;
import com.hybrid.internship.exception.CurrentlyRentingException;
import com.hybrid.internship.model.ERole;
import com.hybrid.internship.model.User;
import com.hybrid.internship.repository.RoleRepository;
import com.hybrid.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository repository;
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User insert(User newUser) {
        Objects.requireNonNull(newUser);
        encryptPassword(newUser);
        newUser.setRole(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new EntityNotFoundException("No user role found")));
        return repository.save(newUser);
    }

    public User findById(Long id) throws EntityNotFoundException {
        Objects.requireNonNull(id);
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No user with this ID found"));
    }

    public void delete(Long id) {
        User userForDeletion = findById(id);
        if (isUserRenting(id)){
            throw new CurrentlyRentingException();
        }
        repository.delete(userForDeletion);
    }

    public User update(Long id, User newUser) {
        Objects.requireNonNull(newUser);
        encryptPassword(newUser);
        User userForUpdate = new User(id,
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getFirstName(),
                newUser.getLastName());
        newUser.setRole(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new EntityNotFoundException("No user role found")));
        return repository.save(userForUpdate);
    }

    public User insertAdmin(User newUser) {
        Objects.requireNonNull(newUser);
        encryptPassword(newUser);
        newUser.setRole(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new EntityNotFoundException("No admin role found")));
        return repository.save(newUser);
    }

    private boolean isUserRenting(Long id) {
        Objects.requireNonNull(id);
        return !bookCopyService.findByUserId(id).isEmpty();
    }
    private void encryptPassword(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
}
