package com.hybrid.internship.dto.mapper;

import com.hybrid.internship.dto.BookCopyRequestDTO;
import com.hybrid.internship.dto.BookCopyResponseDTO;
import com.hybrid.internship.model.Book;
import com.hybrid.internship.model.BookCopy;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {

     public BookCopyMapper() {}

     public BookCopy fromDTO(BookCopyRequestDTO bookCopyDTO)
     {
          Book book = new Book();
          book.setId(bookCopyDTO.getBookId());
          return new BookCopy(bookCopyDTO.getCode(), book);
     }
     public BookCopyRequestDTO toRequestDTO(BookCopy bookCopy){
          return new BookCopyRequestDTO(bookCopy.getCode(), bookCopy.getBook().getId());
     }
     public BookCopy fromDTO(BookCopyResponseDTO bookCopyDTO)
     {
          Book book = new Book();
          book.setId(bookCopyDTO.getBookId());
          return new BookCopy(bookCopyDTO.getId(), bookCopyDTO.getCode(), book);
     }
     public BookCopyResponseDTO toResponseDTO(BookCopy bookCopy){
          return new BookCopyResponseDTO(bookCopy.getId(), bookCopy.getCode(), bookCopy.getBook().getId());
     }
}

