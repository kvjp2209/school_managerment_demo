package com.example.kiennt_demo2.repository;

import com.example.kiennt_demo2.entity.Role;
import com.example.kiennt_demo2.utils.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

    Role findByName(String name);
}
