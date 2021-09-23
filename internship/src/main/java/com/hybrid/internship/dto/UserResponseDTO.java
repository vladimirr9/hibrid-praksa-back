package com.hybrid.internship.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserResponseDTO {

    @NotNull(message = "ID cannot be null")
    private Long id;
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    public UserResponseDTO(Long id, String email, String firstName, String lastName) {
        this.id = Objects.requireNonNull(id);
        this.email = Objects.requireNonNull(email);
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
