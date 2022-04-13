package com.example.kiennt_demo2.controller;

import com.example.kiennt_demo2.model.Employee;
import com.example.kiennt_demo2.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "")
    public Employee createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return employee;
    }

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping(value = "/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        Employee result = employeeService.deleteEmployee(id);
        if (result == null) {
            Object ResponseEntity = null;
        }
        return "Delete finished!!";
    }

    @PutMapping(value = "/{id}")
    public Employee updateEmployee(@Validated @RequestBody Employee employee, @PathVariable("id") int id) {
        Employee result = employeeService.updateEmployee(employee, id);

        return result;
    }
}
