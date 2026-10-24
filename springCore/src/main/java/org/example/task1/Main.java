package org.example.task1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Employee employee = context.getBean(Employee.class);

        employee.displayEmployeeDetails();

    }
}
