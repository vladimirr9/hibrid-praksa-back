package com.hybrid.internship.repository;

import com.hybrid.internship.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    List<BookCopy> findByUserId(Long user_id);
}
