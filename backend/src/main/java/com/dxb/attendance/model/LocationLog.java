package com.dxb.attendance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Location_Logs")
public class LocationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    // Linking this log to a specific attendance session
    @ManyToOne
    @JoinColumn(name = "attendance_id", nullable = false)
    private Attendance attendance; // Maps to AttendanceID relation

    private LocalDateTime time; // Maps to Time
    private Double latitude; // Maps to Latitude
    private Double longitude; // Maps to Longitude
    private String ssid; // Maps to SSID
    private String bssid; // Maps to BSSID (Router MAC)
    private Double accuracy; // Maps to Accuracy
    private Boolean insideOffice; // Maps to InsideOffice (Yes/No)

    // TODO: Right-click in VS Code -> Source Action -> Generate Getters and Setters
}