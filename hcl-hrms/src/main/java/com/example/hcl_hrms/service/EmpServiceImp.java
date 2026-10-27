
package com.example.hcl_hrms.service;

import com.example.hcl_hrms.entity.Employee;
import com.example.hcl_hrms.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImp {

    private final EmployeeRepo emprepo;

    public EmpServiceImp(EmployeeRepo emprepo) {
        this.emprepo = emprepo;
    }

    public String addEmp() {
        Employee emp = new Employee(
                200,
                "Rahul",
                "SD",
                "rahul@gmail.com",
                "Java"
        );

        return emprepo.addEmp(emp);
    }

    public Employee getEmpbyId(long emp_id) {
        return emprepo.getEmpbyId(emp_id);
    }

    public List<Employee> getEmp() {
        return emprepo.getEmp();
    }
}
