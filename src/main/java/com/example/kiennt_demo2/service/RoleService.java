package com.example.kiennt_demo2.service;

import com.example.kiennt_demo2.entity.Role;
import com.example.kiennt_demo2.entity.User;
import com.example.kiennt_demo2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }


    public Role createUser(Role role) {
        return roleRepository.save(role);
    }
}
