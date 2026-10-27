package com.example.hcl_hrms.controller;

import com.example.hcl_hrms.entity.Employee;
import com.example.hcl_hrms.service.EmpServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmpServiceImp empServiceImp;


    @GetMapping("/addEmp")
    public String addnewEmployee() {
        return empServiceImp.addEmp();
    }

    @GetMapping("/getEmp")
    public List<Employee> getEmp() {
        return empServiceImp.getEmp();
    }

    @GetMapping("/emp/{emp_id}")
    public ResponseEntity<?> getEmpbyId(
            @PathVariable("emp_id") long emp_id) {

        Employee emp = empServiceImp.getEmpbyId(emp_id);

        if (emp == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Employee not found with ID: " + emp_id);
        }

        return ResponseEntity.ok(emp);
    }


}
