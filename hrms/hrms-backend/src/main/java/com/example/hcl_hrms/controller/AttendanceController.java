
package com.example.hcl_hrms.controller;

import com.example.hcl_hrms.entity.Attendance;
import com.example.hcl_hrms.service.AttendanceService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // 1. Mark attendance
    @PostMapping
    public ResponseEntity<Attendance> markAttendance(
            @RequestBody Attendance attendance) {

        Attendance saved =
                attendanceService.markAttendance(attendance);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 2. Get all attendance records
    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendance() {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance()
        );
    }

    // 3. Get attendance by attendance ID
    @GetMapping("/{attendanceId}")
    public ResponseEntity<Attendance> getAttendanceById(
            @PathVariable Long attendanceId) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceById(attendanceId)
        );
    }

    // 4. Get attendance by employee ID
    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployeeId(
            @PathVariable Long empId) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceByEmployeeId(empId)
        );
    }

    // 5. Get attendance by date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Attendance>> getAttendanceByDate(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceByDate(date)
        );
    }

    // 6. Get attendance by employee ID and date
    @GetMapping("/employee/{empId}/date/{date}")
    public ResponseEntity<List<Attendance>>
    getAttendanceByEmployeeAndDate(
            @PathVariable Long empId,
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceByEmployeeAndDate(
                        empId, date
                )
        );
    }

    // 7. Check out
    @PatchMapping("/{attendanceId}/checkout")
    public ResponseEntity<Attendance> checkOut(
            @PathVariable Long attendanceId) {

        return ResponseEntity.ok(
                attendanceService.checkOut(attendanceId)
        );
    }

    // 8. Update attendance
    @PutMapping("/{attendanceId}")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable Long attendanceId,
            @RequestBody Attendance attendance) {

        return ResponseEntity.ok(
                attendanceService.updateAttendance(
                        attendanceId, attendance
                )
        );
    }

    // 9. Delete attendance
    @DeleteMapping("/{attendanceId}")
    public ResponseEntity<Void> deleteAttendance(
            @PathVariable Long attendanceId) {

        attendanceService.deleteAttendance(attendanceId);

        return ResponseEntity.noContent().build();
    }
}
