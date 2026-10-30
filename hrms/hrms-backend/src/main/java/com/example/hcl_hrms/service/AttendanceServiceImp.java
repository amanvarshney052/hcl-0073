
package com.example.hcl_hrms.service;

import com.example.hcl_hrms.entity.Attendance;
import com.example.hcl_hrms.entity.Employee;
import com.example.hcl_hrms.repository.AttendanceRepo;
import com.example.hcl_hrms.repository.EmployeeRepo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AttendanceServiceImp implements AttendanceService {

    private final AttendanceRepo attendanceRepo;
    private final EmployeeRepo employeeRepo;

    public AttendanceServiceImp(
            AttendanceRepo attendanceRepo,
            EmployeeRepo employeeRepo) {
        this.attendanceRepo = attendanceRepo;
        this.employeeRepo = employeeRepo;
    }

    // 1. Mark attendance
    @Override
    public Attendance markAttendance(Attendance attendance) {

        if (attendance == null || attendance.getEmployee() == null
                || attendance.getEmployee().getEmpId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Employee ID is required"
            );
        }

        Long empId = attendance.getEmployee().getEmpId();

        Employee employee = employeeRepo.findById(empId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee not found with ID: " + empId
                ));

        if (attendance.getAttendancedate() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Attendance date is required"
            );
        }

        LocalDate date = attendance.getAttendancedate();

        if (attendanceRepo
                .existsByEmployee_EmpIdAndAttendancedate(empId, date)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Attendance already exists for this employee on " + date
            );
        }

        if (attendance.getInTime() == null) {
            attendance.setInTime(LocalDateTime.now());
        }

        if (!attendance.getInTime().toLocalDate().equals(date)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Check-in date must match attendance date"
            );
        }

        if (attendance.getOutTime() != null) {
            validateTimes(attendance.getInTime(), attendance.getOutTime());

            if (!attendance.getOutTime().toLocalDate().equals(date)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Check-out date must match attendance date"
                );
            }
        }

        // Use the employee loaded from the database
        attendance.setEmployee(employee);

        // Do not accept a client-supplied generated ID
        attendance.setAttendanceId(null);

        return attendanceRepo.save(attendance);
    }

    // 2. Get all attendance records
    @Override
    @Transactional(readOnly = true)
    public List<Attendance> getAllAttendance() {
        return attendanceRepo.findAll();
    }

    // 3. Get attendance by attendance ID
    @Override
    @Transactional(readOnly = true)
    public Attendance getAttendanceById(Long attendanceId) {
        return attendanceRepo.findById(attendanceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Attendance not found with ID: " + attendanceId
                ));
    }

    // 4. Get attendance by employee ID
    @Override
    @Transactional(readOnly = true)
    public List<Attendance> getAttendanceByEmployeeId(Long empId) {

        if (!employeeRepo.existsById(empId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found with ID: " + empId
            );
        }

        return attendanceRepo.findByEmployee_EmpId(empId);
    }

    // 5. Get attendance by date
    @Override
    @Transactional(readOnly = true)
    public List<Attendance> getAttendanceByDate(LocalDate date) {

        if (date == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Attendance date is required"
            );
        }

        return attendanceRepo.findByAttendancedate(date);
    }

    // 6. Get attendance by employee ID and date
    @Override
    @Transactional(readOnly = true)
    public List<Attendance> getAttendanceByEmployeeAndDate(
            Long empId,
            LocalDate date) {

        if (!employeeRepo.existsById(empId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found with ID: " + empId
            );
        }

        if (date == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Attendance date is required"
            );
        }

        return attendanceRepo
                .findByEmployee_EmpIdAndAttendancedate(empId, date)
                .stream()
                .toList();
    }

    // 7. Check out an employee
    @Override
    public Attendance checkOut(Long attendanceId) {

        Attendance attendance = getAttendanceById(attendanceId);

        if (attendance.getOutTime() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Employee has already checked out"
            );
        }

        LocalDateTime outTime = LocalDateTime.now();

        validateTimes(attendance.getInTime(), outTime);

        if (!outTime.toLocalDate().equals(attendance.getAttendancedate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Check-out must occur on the attendance date"
            );
        }

        attendance.setOutTime(outTime);

        return attendanceRepo.save(attendance);
    }

    // 8. Update attendance
    @Override
    public Attendance updateAttendance(
            Long attendanceId,
            Attendance updatedAttendance) {

        if (updatedAttendance == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Attendance details are required"
            );
        }

        Attendance existing = getAttendanceById(attendanceId);

        // Keep employee ID immutable in this update operation
        if (updatedAttendance.getEmployee() != null
                && updatedAttendance.getEmployee().getEmpId() != null
                && !updatedAttendance.getEmployee().getEmpId()
                .equals(existing.getEmployee().getEmpId())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Changing the employee requires a separate operation"
            );
        }

        LocalDate newDate = updatedAttendance.getAttendancedate() != null
                ? updatedAttendance.getAttendancedate()
                : existing.getAttendancedate();

        LocalDateTime newInTime = updatedAttendance.getInTime() != null
                ? updatedAttendance.getInTime()
                : existing.getInTime();

        LocalDateTime newOutTime = updatedAttendance.getOutTime() != null
                ? updatedAttendance.getOutTime()
                : existing.getOutTime();

        if (newInTime != null
                && !newInTime.toLocalDate().equals(newDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Check-in date must match attendance date"
            );
        }

        if (newOutTime != null) {
            validateTimes(newInTime, newOutTime);

            if (!newOutTime.toLocalDate().equals(newDate)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Check-out date must match attendance date"
                );
            }
        }

        Long empId = existing.getEmployee().getEmpId();

        boolean duplicate = attendanceRepo
                .existsByEmployee_EmpIdAndAttendancedate(empId, newDate);

        if (duplicate && !newDate.equals(existing.getAttendancedate())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Attendance already exists for this employee on " + newDate
            );
        }

        existing.setAttendancedate(newDate);
        existing.setInTime(newInTime);
        existing.setOutTime(newOutTime);

        return attendanceRepo.save(existing);
    }

    // 9. Delete attendance
    @Override
    public void deleteAttendance(Long attendanceId) {

        if (!attendanceRepo.existsById(attendanceId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Attendance not found with ID: " + attendanceId
            );
        }

        attendanceRepo.deleteById(attendanceId);
    }

    // Common time validation
    private void validateTimes(
            LocalDateTime inTime,
            LocalDateTime outTime) {

        if (inTime == null || outTime == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Check-in and check-out times are required"
            );
        }

        if (outTime.isBefore(inTime)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Check-out time cannot be before check-in time"
            );
        }
    }
}
