package com.hybrid.internship.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BookCopy> bookCopies;
    @ManyToOne()
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User() {
    }

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

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = Objects.requireNonNull(password);
    }

    public String toString() {
        return email;
    }

    public void setRole(Role role) {
        this.role = Objects.requireNonNull(role);
    }
}
