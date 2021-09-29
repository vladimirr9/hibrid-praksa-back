package com.hybrid.internship.service;


import com.hybrid.internship.model.Role;

import java.util.List;

public interface RoleService {
    /**
     *
     * @return - All roles within the system
     */
    List<Role> findAll();

    Role insert(Role role);
}
