package com.dxb.attendance.controller;

import com.dxb.attendance.service.AttendanceVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceVerificationService verificationService;

    // Data Transfer Object to receive payload from mobile app
    public static class ClockInRequest {
        public String employeeId;
        public String ssid;
        public double latitude;
        public double longitude;
        public String bssid;
    }

    @PostMapping("/clock-in")
    public ResponseEntity<String> processClockIn(@RequestBody ClockInRequest request) {
        boolean isVerified = verificationService.verifyLocation(
            request.ssid, request.latitude, request.longitude
        );

        if (isVerified) {
            // TODO: Save Attendance record via AttendanceRepository
            return ResponseEntity.ok("Status = Present");
        } else {
            return ResponseEntity.status(403).body("Reject: Outside Office");
        }
    }
}