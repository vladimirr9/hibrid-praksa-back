package com.hybrid.internship.service;

import com.hybrid.internship.exception.EntityNotFoundException;
import com.hybrid.internship.model.Book;
import com.hybrid.internship.repository.BookRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    void setup() {
        bookService = new BookServiceImpl(bookRepository);
    }


    @Test
    void testUpdateSuccess() {
        Book book = new Book("Neko", "Nesto");
        Long id = 1L;
        ArgumentCaptor<Book> argumentCaptor = ArgumentCaptor.forClass(Book.class);
        when(bookRepository.save(argumentCaptor.capture())).thenReturn(book);

        bookService.update(id, book);
        verify(bookRepository, times(1)).save(argumentCaptor.getValue());
        assertEquals(id, argumentCaptor.getValue().getId());
    }
    @Test()
    void testGetOneFail() {
        Long id = 1L;
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        Throwable exception = assertThrows(EntityNotFoundException.class, () -> bookService.findById(id));
    }
}
