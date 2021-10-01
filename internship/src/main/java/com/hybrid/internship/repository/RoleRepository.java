package com.hybrid.internship.repository;

import com.hybrid.internship.model.ERole;
import com.hybrid.internship.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}