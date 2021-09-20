package com.hybrid.internship.repository;

import com.hybrid.internship.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
