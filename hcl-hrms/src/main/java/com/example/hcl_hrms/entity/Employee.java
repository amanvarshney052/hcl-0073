package com.example.hcl_hrms.entity;

public class Employee {
    private long emp_id;
    private String emp_name;
    private String designation;
    private String email;
    private String progStack;



    public long getEmp_id() {
        return emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getEmail() {
        return email;
    }

    public String getProgStack() {
        return progStack;
    }

    public void setEmp_id(long emp_id) {
        this.emp_id = emp_id;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProgStack(String progStack) {
        this.progStack = progStack;
    }

    public Employee(long emp_id, String emp_name,
                    String designation, String email,
                    String progStack) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.designation = designation;
        this.email = email;
        this.progStack = progStack;
    }


}
