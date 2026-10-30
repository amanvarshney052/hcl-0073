
package com.example.hcl_hrms.service;

import com.example.hcl_hrms.entity.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    Attendance markAttendance(Attendance attendance);

    List<Attendance> getAllAttendance();

    Attendance getAttendanceById(Long attendanceId);

    List<Attendance> getAttendanceByEmployeeId(Long empId);

    List<Attendance> getAttendanceByDate(LocalDate date);

    List<Attendance> getAttendanceByEmployeeAndDate(
            Long empId,
            LocalDate date
    );

    Attendance checkOut(Long attendanceId);

    Attendance updateAttendance(
            Long attendanceId,
            Attendance attendance
    );

    void deleteAttendance(Long attendanceId);
}
