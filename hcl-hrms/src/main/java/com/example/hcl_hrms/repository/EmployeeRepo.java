package com.example.hcl_hrms.repository;


import com.example.hcl_hrms.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo {
    String addEmp(Employee emp);
    List<Employee> getEmp();
    Employee getEmpbyId(long emp_id);
    String deletebyID(long emp_id);
    String updatebyId(long emp_id, Employee updatedEmp);


}
