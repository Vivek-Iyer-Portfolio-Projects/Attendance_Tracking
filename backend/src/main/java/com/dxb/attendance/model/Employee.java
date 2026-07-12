package com.dxb.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId; // Maps to EmployeeID
    
    private String name; // Maps to Name
    private String department; // Maps to Department
    private String registeredDevice; // Maps to Registered Device (IMEI or UUID)
    private String status; // Maps to Status

    // TODO: Right-click in VS Code -> Source Action -> Generate Getters and Setters
}