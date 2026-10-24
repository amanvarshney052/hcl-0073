package org.example.task1;

import org.springframework.stereotype.Component;

@Component
public class Employee {
    private final Salary salary;

    public Employee(Salary salary) {
        this.salary = salary;
    }

    public void displayEmployeeDetails() {
        System.out.println("Employee Salary Details:-");
        salary.displaySalary();
    }
}
