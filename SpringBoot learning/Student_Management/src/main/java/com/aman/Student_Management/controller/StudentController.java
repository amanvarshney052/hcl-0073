package com.aman.Student_Management.controller;

import com.aman.Student_Management.model.Student;
import com.aman.Student_Management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/")
    public String welcome(){
        return "Welcome to Abes Student Portal";
    }

    @GetMapping("/addStudent")
    public String addStudent(){
        return studentService.createStudent();
    }

    @GetMapping("/findbyId/{student_id}")
    public Student getStudentbyId(@PathVariable long student_id){
        return studentService.getStudentbyId(student_id);

    }
    @DeleteMapping("/deleteStudent/{student_id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long student_id) {
        boolean deleted = studentService.deleteStudent(student_id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found with ID: " + student_id);
        }
        return ResponseEntity.ok("Student deleted successfully");
    }
    @PutMapping("/updateStudent/{student_id}")
    public ResponseEntity<?> updateStudent(@PathVariable long student_id,
                                           @RequestBody Student student) {

        Student updated = studentService.updateStudent(student_id, student);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found with ID: " + student_id);
        }
        return ResponseEntity.ok(updated);
}}
