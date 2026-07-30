package com.dxb.attendance.controller;

import com.dxb.attendance.model.Employee;
import com.dxb.attendance.repository.AttendanceRepository;
import com.dxb.attendance.repository.EmployeeRepository;
import com.dxb.attendance.service.AttendanceVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dxb.attendance.model.Attendance;
import java.time.LocalDateTime;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // <-- Whitelists the Angular Dashboard
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AttendanceVerificationService verificationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    // A temporary class to catch the data sent by the mobile app
    public static class ClockInRequest {
        public String employeeId;
        public String ssid;
        public double latitude;
        public double longitude;
        public String bssid;
    }

    @PostMapping("/verify")
    public ResponseEntity verifyClockIn(@RequestBody ClockInRequest request) {
        boolean isVerified = verificationService.verifyLocation(
            request.ssid, request.latitude, request.longitude
        );

        if (isVerified) {
           // --- 1. Create the database record ---
            Attendance newRecord = new Attendance();
            
            // Convert the String ID from React Native to a Long (if your Employee ID is a Long)
            Long parsedId = Long.parseLong(request.employeeId);
            
            // Fetch the actual Employee object from the database
            Employee employee = employeeRepository.findById(parsedId).orElse(null);
            
            if (employee == null) {
                return ResponseEntity.status(404).body("Error: Employee ID not found in database.");
            }

            // Set the fields
            newRecord.setEmployee(employee); 
            newRecord.setClockIn(LocalDateTime.now());
            newRecord.setStatus("PRESENT");

            // --- 2. Save it to PostgreSQL ---
            attendanceRepository.save(newRecord);

            // --- 3. Return the success response ---
            return ResponseEntity.ok("Status = Present");
        } else {
            return ResponseEntity.status(403).body("Reject: Outside Office");
        }
    }

    // Bypasses H2 Console to view database directly
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    // NEW ENDPOINT: Fetches the attendance logs for the Angular Dashboard
    @GetMapping("/logs")
    public ResponseEntity<List<Attendance>> getAllLogs() {
        return ResponseEntity.ok(attendanceRepository.findAll());
    }
}