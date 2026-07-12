package com.dxb.attendance.service;

import org.springframework.stereotype.Service;

@Service
public class AttendanceVerificationService {

    // Define the office criteria from the PDF spec
    private static final String OFFICE_SSID = "DIH-STAFF";
    private static final double OFFICE_LAT = 25.253174;
    private static final double OFFICE_LON = 55.365673;
    private static final double ALLOWED_RADIUS_METERS = 100.0;

    /**
     * Step 2 - Verify Office Location Option A & B
     */
    public boolean verifyLocation(String userSsid, double userLat, double userLon) {
        // Option A: Check WiFi SSID
        if (OFFICE_SSID.equals(userSsid)) {
            return true; // Confidence High
        }

        // Option B: Proceed to GPS verification
        double distance = calculateDistance(OFFICE_LAT, OFFICE_LON, userLat, userLon);
        return distance <= ALLOWED_RADIUS_METERS;
    }

    /**
     * Calculates the distance between two GPS points using the Haversine formula.
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371000; // Earth radius in meters

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return R * c; // Returns distance in meters
    }
}