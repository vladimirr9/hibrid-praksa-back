package com.hybrid.internship.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User() {}

    public User(Long id, String email, String password, String firstName, String lastName) {
        this.id = Objects.requireNonNull(id);
        init(email, password, firstName, lastName);
    }
    public User(String email, String password, String firstName, String lastName) {
        init(email, password, firstName, lastName);
    }

    private void init(String email, String password, String firstName, String lastName) {
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
