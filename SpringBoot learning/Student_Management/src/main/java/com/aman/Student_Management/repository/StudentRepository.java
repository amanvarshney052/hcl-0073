package com.aman.Student_Management.repository;

import com.aman.Student_Management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
