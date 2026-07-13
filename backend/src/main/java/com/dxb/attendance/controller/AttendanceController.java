package com.dxb.attendance.controller;

import com.dxb.attendance.model.Employee;
import com.dxb.attendance.repository.EmployeeRepository;
import com.dxb.attendance.service.AttendanceVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceVerificationService verificationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    // A temporary class to catch the data sent by the mobile app
    public static class ClockInRequest {
        public String ssid;
        public double latitude;
        public double longitude;
        public String bssid;
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyClockIn(@RequestBody ClockInRequest request) {
        boolean isVerified = verificationService.verifyLocation(
            request.ssid, request.latitude, request.longitude
        );

        if (isVerified) {
            return ResponseEntity.ok("Status = Present");
        } else {
            return ResponseEntity.status(403).body("Reject: Outside Office");
        }
    }

    // NEW ENDPOINT: Bypasses H2 Console to view database directly
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }
}