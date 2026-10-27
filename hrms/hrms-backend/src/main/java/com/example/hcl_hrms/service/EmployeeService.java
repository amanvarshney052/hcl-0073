package com.example.hcl_hrms.service;

import com.example.hcl_hrms.entity.Employee;

import java.util.List;

public interface EmployeeService {

    String addEmployee(Employee employee);

    String registerEmployee(Employee employee);

    Employee loginEmployee(String email, String password);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long id);

    String updateEmployeeById(long id, Employee employee);

    String deleteEmployeeById(long id);
}