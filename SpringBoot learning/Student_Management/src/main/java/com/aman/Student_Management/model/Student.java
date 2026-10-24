package com.aman.Student_Management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id

    private Long Student_id;

    private String name;
    private String department;
    private String email;
    private String address;
    private long phone;

    public Student(){

    }

    public long getStudent_id() {
        return Student_id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public long getPhone() {
        return phone;
    }

    public void setStudent_id(long student_id) {
        Student_id = student_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Student(long student_id, String name, String department, String email, String address, long phone) {
        Student_id = student_id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
}
