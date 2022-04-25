package com.example.kiennt_demo2.controller;

import com.example.kiennt_demo2.common.ApiResponse;
import com.example.kiennt_demo2.entity.Employee;
import com.example.kiennt_demo2.service.EmployeeService;
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
    public ApiResponse getAllEmployees() {
        ApiResponse result = employeeService.getAllEmployees();
        return result;
    }

    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteEmployee(@PathVariable("id") int id) {
        ApiResponse result = employeeService.deleteEmployee(id);
        return result;
    }

    @PutMapping(value = "/{id}")
    public ApiResponse updateEmployee(@Validated @RequestBody Employee employee, @PathVariable("id") int id) {
        ApiResponse result = employeeService.updateEmployee(employee, id);

        return result;
    }
}
