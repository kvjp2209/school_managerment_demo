package com.example.kiennt_demo2.service;

import com.example.kiennt_demo2.common.ApiResponse;
import com.example.kiennt_demo2.entity.Employee;
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
    public ApiResponse getAllEmployees() {
        ApiResponse apiResponse = new ApiResponse();
        List<Employee> employees = employeeRepository.findAll();
        apiResponse.setData(employees);
        return apiResponse;
    }

    //delete the single employee
    public ApiResponse deleteEmployee(long id) {
        ApiResponse apiResponse = new ApiResponse();
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            apiResponse.setError("Not Found!!!");
            return apiResponse;
        }
        employeeRepository.delete(employee);
        apiResponse.setData("success!!!");
        return apiResponse;
    }

    public Employee findEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id);
        return employee;
    }

    public ApiResponse updateEmployee(Employee e, int id) {
        ApiResponse apiResponse = new ApiResponse();

        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            apiResponse.setError("Not Found!!!");
            return apiResponse;
        }
        employee.setName(e.getName());
        employee.setAge(e.getAge());
        employee.setAddress(e.getAddress());
        employee.setRoleId(e.getRoleId());

        employeeRepository.save(employee);
        apiResponse.setData(employee);

        return apiResponse;
    }
}
