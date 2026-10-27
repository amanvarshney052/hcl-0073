package com.example.hcl_hrms.service;

import com.example.hcl_hrms.entity.Employee;
import com.example.hcl_hrms.repository.EmployeeRepo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class EmpServiceImp implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    public EmpServiceImp(
            EmployeeRepo employeeRepo,
            PasswordEncoder passwordEncoder) {

        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. Add a new employee
    @Override
    public String addEmployee(Employee employee) {
        return saveEmployee(employee);
    }

    // 2. Register a new employee
    @Override
    public String registerEmployee(Employee employee) {
        return saveEmployee(employee);
    }

    // Common method for adding and registering employees
    private String saveEmployee(Employee employee) {

        validateEmployee(employee);

        String email = normalizeEmail(employee.getEmail());

        if (employeeRepo.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Employee already exists with email: " + email
            );
        }

        employee.setEmpId(null);
        employee.setEmail(email);

        // Never store the raw password
        employee.setPassword(
                passwordEncoder.encode(employee.getPassword())
        );

        Employee savedEmployee = employeeRepo.save(employee);

        return "Employee saved successfully with ID: "
                + savedEmployee.getEmpId();
    }

    // 3. Login employee
    @Override
    @Transactional(readOnly = true)
    public Employee loginEmployee(String email, String password) {

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {
            return null;
        }

        String normalizedEmail = normalizeEmail(email);

        return employeeRepo.findByEmail(normalizedEmail)
                .filter(employee ->
                        employee.getPassword() != null
                                && passwordEncoder.matches(
                                password,
                                employee.getPassword()
                        )
                )
                .orElse(null);
    }

    // 4. Get all employees
    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    // 5. Get employee by ID
    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(long id) {

        if (id <= 0) {
            return null;
        }

        return employeeRepo.findById(id).orElse(null);
    }

    // 6. Update employee by ID
    @Override
    public String updateEmployeeById(
            long id,
            Employee updatedEmployee) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Employee ID must be positive"
            );
        }

        if (updatedEmployee == null) {
            throw new IllegalArgumentException(
                    "Employee details are required"
            );
        }

        Employee existingEmployee = employeeRepo.findById(id)
                .orElse(null);

        if (existingEmployee == null) {
            return "Employee not found with ID: " + id;
        }

        // Update employee name
        if (updatedEmployee.getEmpName() != null) {
            existingEmployee.setEmpName(
                    updatedEmployee.getEmpName()
            );
        }

        // Update designation
        if (updatedEmployee.getDesignation() != null) {
            existingEmployee.setDesignation(
                    updatedEmployee.getDesignation()
            );
        }

        // Update email only if it is not used by another employee
        if (updatedEmployee.getEmail() != null) {

            if (updatedEmployee.getEmail().isBlank()) {
                throw new IllegalArgumentException(
                        "Email cannot be blank"
                );
            }

            String newEmail = normalizeEmail(
                    updatedEmployee.getEmail()
            );

            if (employeeRepo.existsByEmailAndEmpIdNot(
                    newEmail, id)) {

                throw new IllegalArgumentException(
                        "Employee already exists with email: "
                                + newEmail
                );
            }

            existingEmployee.setEmail(newEmail);
        }

        // Update programming stack
        if (updatedEmployee.getProgStack() != null) {
            existingEmployee.setProgStack(
                    updatedEmployee.getProgStack()
            );
        }

        // Update password only when a new password is provided
        if (updatedEmployee.getPassword() != null
                && !updatedEmployee.getPassword().isBlank()) {

            existingEmployee.setPassword(
                    passwordEncoder.encode(
                            updatedEmployee.getPassword()
                    )
            );
        }

        employeeRepo.save(existingEmployee);

        return "Employee updated successfully with ID: " + id;
    }

    // 7. Delete employee by ID
    @Override
    public String deleteEmployeeById(long id) {

        if (id <= 0 || !employeeRepo.existsById(id)) {
            return "Employee not found with ID: " + id;
        }

        employeeRepo.deleteById(id);

        return "Employee deleted successfully with ID: " + id;
    }

    // Validate registration details
    private void validateEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException(
                    "Employee details are required"
            );
        }

        if (employee.getEmail() == null
                || employee.getEmail().isBlank()) {

            throw new IllegalArgumentException(
                    "Email is required"
            );
        }

        if (!employee.getEmail().trim().matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

            throw new IllegalArgumentException(
                    "Please enter a valid email address"
            );
        }

        if (employee.getPassword() == null
                || employee.getPassword().isBlank()) {

            throw new IllegalArgumentException(
                    "Password is required"
            );
        }

        if (employee.getPassword().length() < 8) {
            throw new IllegalArgumentException(
                    "Password must contain at least 8 characters"
            );
        }
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}