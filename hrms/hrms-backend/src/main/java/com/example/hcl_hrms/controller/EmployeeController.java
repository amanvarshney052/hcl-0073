
package com.example.hcl_hrms.controller;

import com.example.hcl_hrms.entity.Employee;
import com.example.hcl_hrms.service.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/v1/user")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. Welcome endpoint
    @GetMapping({"", "/"})
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity.ok(
                Map.of("message", "Welcome to ABES HRMS Portal")
        );
    }

    // 2. Add employee
    @PostMapping("/addEmp")
    public ResponseEntity<Map<String, String>> addEmployee(
            @RequestBody Employee employee) {

        try {
            String result = employeeService.addEmployee(employee);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", result));

        } catch (IllegalArgumentException e) {
            return errorResponse(e);
        }
    }

    // 3. Register employee
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerEmployee(
            @RequestBody Employee employee) {

        try {
            String result =
                    employeeService.registerEmployee(employee);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", result));

        } catch (IllegalArgumentException e) {
            return errorResponse(e);
        }
    }

    // 4. Get all employees
    @GetMapping("/getEmp")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }

    // 5. Get employee by ID
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<?> getEmployeeById(
            @PathVariable("id") long id) {

        if (id <= 0) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", "Employee ID must be positive")
            );
        }

        Employee employee = employeeService.getEmployeeById(id);

        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("message",
                            "Employee not found with ID: " + id)
            );
        }

        return ResponseEntity.ok(employee);
    }

    // 6. Update employee
    @PutMapping("/updateEmp/{id}")
    public ResponseEntity<Map<String, String>> updateEmployee(
            @PathVariable("id") long id,
            @RequestBody Employee employee) {

        try {
            String result =
                    employeeService.updateEmployeeById(id, employee);

            if (result.startsWith("Employee not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", result));
            }

            return ResponseEntity.ok(Map.of("message", result));

        } catch (IllegalArgumentException e) {
            return errorResponse(e);
        }
    }

    // 7. Delete employee
    @DeleteMapping("/deleteEmp/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(
            @PathVariable("id") long id) {

        if (id <= 0) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", "Employee ID must be positive")
            );
        }

        String result = employeeService.deleteEmployeeById(id);

        if (result.startsWith("Employee not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", result));
        }

        return ResponseEntity.ok(Map.of("message", result));
    }

    // Common error response
    private ResponseEntity<Map<String, String>> errorResponse(
            IllegalArgumentException exception) {

        String message = exception.getMessage();

        if (message == null || message.isBlank()) {
            message = "Invalid employee details";
        }

        HttpStatus status =
                message.startsWith("Employee already exists")
                        ? HttpStatus.CONFLICT
                        : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status)
                .body(Map.of("message", message));
    }
}
