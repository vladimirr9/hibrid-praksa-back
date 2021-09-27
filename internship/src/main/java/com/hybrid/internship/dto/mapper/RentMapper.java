package com.hybrid.internship.dto.mapper;

import com.hybrid.internship.dto.RentResponseDTO;
import com.hybrid.internship.model.BookCopy;
import org.springframework.stereotype.Component;


@Component
public class RentMapper {


    public RentResponseDTO toResponseDTO(BookCopy bookCopy, Long userId, String userEmail) {
        return new RentResponseDTO(bookCopy.getId(),
                bookCopy.getCode(),
                bookCopy.getBook().getId(),
                userId,
                userEmail);
    }

    public RentResponseDTO toResponseDTO(BookCopy bookCopy) {
        return new RentResponseDTO(bookCopy.getId(),
                bookCopy.getCode(),
                bookCopy.getBook().getId(),
                bookCopy.getUser().getId(),
                bookCopy.getUser().getEmail());
    }
}
