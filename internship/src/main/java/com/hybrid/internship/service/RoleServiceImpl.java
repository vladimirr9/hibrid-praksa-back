package com.hybrid.internship.service;

import com.hybrid.internship.model.Role;
import com.hybrid.internship.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role insert(Role role) {
        return roleRepository.save(role);
    }

}
