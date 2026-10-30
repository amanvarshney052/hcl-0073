
package com.example.hcl_hrms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "attendance",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_attendance_employee_date",
                        columnNames = {"empid", "attendancedate"}
                )
        }
)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendanceid")
    private Long attendanceId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "empid", nullable = false)
    @JsonIgnoreProperties({"password"})
    private Employee employee;

    @Column(name = "attendancedate", nullable = false)
    private LocalDate attendancedate;

    @Column(name = "in_time")
    private LocalDateTime inTime;

    @Column(name = "out_time")
    private LocalDateTime outTime;

    public Attendance() {
    }

    public Attendance(
            Long attendanceId,
            Employee employee,
            LocalDate attendancedate,
            LocalDateTime inTime,
            LocalDateTime outTime) {
        this.attendanceId = attendanceId;
        this.employee = employee;
        this.attendancedate = attendancedate;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getAttendancedate() {
        return attendancedate;
    }

    public void setAttendancedate(LocalDate attendancedate) {
        this.attendancedate = attendancedate;
    }

    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public LocalDateTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }
}
