
package com.example.hcl_hrms.repository;

import com.example.hcl_hrms.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepoImp implements EmployeeRepo {

    private final List<Employee> emplist = new ArrayList<>();

    @Override
    public String addEmp(Employee emp) {
        emplist.add(emp);

        System.out.println("Employee added: " + emp.getEmp_name());
        System.out.println("Employee ID: " + emp.getEmp_id());

        return "Employee Added Successfully";
    }

    @Override
    public List<Employee> getEmp() {
        return emplist;
    }

    @Override
    public Employee getEmpbyId(long emp_id) {
        for (Employee emp : emplist) {
            if (emp.getEmp_id() == emp_id) {
                return emp;
            }
        }

        return null;
    }

    @Override
    public String deletebyID(long emp_id) {
        return "";
    }

    @Override
    public String updatebyId(long emp_id, Employee updatedEmp) {
        return "";
    }
}
