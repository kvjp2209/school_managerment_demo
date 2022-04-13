package com.example.kiennt_demo2.repository;

import com.example.kiennt_demo2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();
    Employee findById(int id);
}
