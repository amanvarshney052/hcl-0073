
package com.example.hcl_hrms.controller;

import com.example.hcl_hrms.entity.Employee;
import com.example.hcl_hrms.service.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
public class LoginController {

    private final EmployeeService employeeService;

    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Employee login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody(required = false)
            Map<String, String> credentials) {

        if (credentials == null) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "message",
                            "Email and password are required"
                    )
            );
        }

        String email = credentials.get("email");
        String password = credentials.get("password");

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {

            return ResponseEntity.badRequest().body(
                    Map.of(
                            "message",
                            "Email and password are required"
                    )
            );
        }

        Employee employee =
                employeeService.loginEmployee(email, password);

        if (employee == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "message",
                            "Invalid email or password"
                    ));
        }

        // Return safe employee details only
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Login successful");
        response.put("employeeId", employee.getEmpId());
        response.put("emp_name", employee.getEmpName());
        response.put("email", employee.getEmail());
        response.put("designation", employee.getDesignation());
        response.put("prog_stack", employee.getProgStack());

        return ResponseEntity.ok(response);
    }
}
