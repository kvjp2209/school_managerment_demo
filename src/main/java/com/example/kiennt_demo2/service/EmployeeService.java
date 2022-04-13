package com.example.kiennt_demo2.service;

import com.example.kiennt_demo2.model.Employee;
import com.example.kiennt_demo2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

//    @Transactional
//    public List<Employee> getAllEmployee() {
//
//    }

    //create employee
    @Transactional
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    //get all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    //delete the single employee
    public Employee deleteEmployee(int eid) {
        Employee employee = employeeRepository.getById(eid);
        employeeRepository.delete(employee);
        return employee;
    }

    public Employee findEmployeeById(int id) {
        Employee employee = employeeRepository.getById(id);
        return employee;
    }

    public Employee updateEmployee(Employee e, int id) {
        Employee employee = employeeRepository.findById(id);
        if (employee != null) {
            employee.setName(e.getName());
            employee.setAge(e.getAge());
            employee.setAddress(e.getAddress());
            employee.setRoleId(e.getRoleId());

            employeeRepository.save(employee);
        }

        return employee;
    }
}
