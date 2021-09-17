package com.hybrid.internship.Repository;

import com.hybrid.internship.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
