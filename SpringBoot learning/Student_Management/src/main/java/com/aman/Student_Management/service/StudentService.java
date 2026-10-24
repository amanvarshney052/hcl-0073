package com.aman.Student_Management.service;

import com.aman.Student_Management.model.Student;
import com.aman.Student_Management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String createStudent(){
        Student s1=new Student(200l,"Aman Varshney","CSE","aman@gmail.com","Rudain",7818015799l);

        studentRepository.save(s1);
        return "Student added succesfully";
    }

    public Student getStudentbyId(long student_id){
        return studentRepository.findById(student_id).orElse(null);

    }
    public boolean deleteStudent(long student_id) {
        if (!studentRepository.existsById(student_id)) {
            return false;
        }
        studentRepository.deleteById(student_id);
        return true;
    }

    public Student updateStudent(long student_id, Student updatedStudent) {
        Student existing = studentRepository.findById(student_id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setName(updatedStudent.getName());
        existing.setDepartment(updatedStudent.getDepartment());
        existing.setEmail(updatedStudent.getEmail());
        existing.setAddress(updatedStudent.getAddress());
        existing.setPhone(updatedStudent.getPhone());

        return studentRepository.save(existing);
    }



}
