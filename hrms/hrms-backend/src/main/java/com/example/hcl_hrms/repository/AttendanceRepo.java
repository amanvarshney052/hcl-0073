
package com.example.hcl_hrms.repository;

import com.example.hcl_hrms.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepo
        extends JpaRepository<Attendance, Long> {

    // Get attendance records for a particular employee
    List<Attendance> findByEmployee_EmpId(Long empId);

    // Get attendance records for a particular date
    List<Attendance> findByAttendancedate(LocalDate date);

    // Get attendance records for an employee on a particular date
    List<Attendance> findByEmployee_EmpIdAndAttendancedate(
            Long empId,
            LocalDate date
    );

    // Check whether attendance already exists for an employee and date
    boolean existsByEmployee_EmpIdAndAttendancedate(
            Long empId,
            LocalDate date
    );
}
