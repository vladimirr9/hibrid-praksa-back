package com.hybrid.internship.repository;

import com.hybrid.internship.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
