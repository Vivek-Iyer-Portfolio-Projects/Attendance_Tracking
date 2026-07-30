package com.dxb.attendance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId; // Maps to AttendanceID

    // Linking this to the Employee table
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // Maps to EmployeeID relation

    private LocalDateTime clockIn; // Maps to ClockIn
    private LocalDateTime clockOut; // Maps to ClockOut
    private Double workHours; // Maps to WorkHours
    private String status; // Maps to Status (e.g., Present, Outside Office)

    // --- GETTERS & SETTERS ---

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) { // <-- Added missing setter
        this.employee = employee;
    }

    public LocalDateTime getClockIn() {
        return clockIn;
    }

    public void setClockIn(LocalDateTime clockIn) { // <-- Added missing setter
        this.clockIn = clockIn;
    }

    public LocalDateTime getClockOut() {
        return clockOut;
    }

    public void setClockOut(LocalDateTime clockOut) {
        this.clockOut = clockOut;
    }

    public Double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Double workHours) {
        this.workHours = workHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { // <-- Added missing setter
        this.status = status;
    }
}