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
    
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getRegisteredDevice() {
        return registeredDevice;
    }
    public void setRegisteredDevice(String registeredDevice) {
        this.registeredDevice = registeredDevice;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}