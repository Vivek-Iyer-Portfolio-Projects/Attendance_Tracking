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

    // TODO: Right-click in VS Code -> Source Action -> Generate Getters and Setters
}