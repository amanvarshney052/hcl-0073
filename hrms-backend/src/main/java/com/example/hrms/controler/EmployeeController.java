package com.example.hrms.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @GetMapping("/")
    public String welcome() {
        return "Welcome to HRMS Portal";
    }

    // Endpoint 1: Addition
    @GetMapping("/add")
    public int add() {
        int a = 10;
        int b = 20;

        return a + b;
    }

    // Endpoint 2: Square Root
    @GetMapping("/squareroot")
    public double squareRoot() {
        double number = 25;

        return Math.sqrt(number);
    }
}
