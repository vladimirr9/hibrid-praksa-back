package com.hybrid.internship.dto.mapper;


import com.hybrid.internship.dto.UserRequestDTO;
import com.hybrid.internship.dto.UserResponseDTO;
import com.hybrid.internship.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserMapper() {
    }

    public User fromDTO(UserRequestDTO userRequestDTO) {
        return new User(userRequestDTO.getEmail(),
                userRequestDTO.getPassword(),
                userRequestDTO.getFirstName(),
                userRequestDTO.getLastName());
    }

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getBookCopies());
    }

}
