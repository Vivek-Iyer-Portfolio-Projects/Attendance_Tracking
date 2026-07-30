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
    
    public Long getLogId() {
        return logId;
    }
    public void setLogId(Long logId) {
        this.logId = logId;
    }
    public Attendance getAttendance() {
        return attendance;
    }
    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public String getSsid() {
        return ssid;
    }
    public void setSsid(String ssid) {
        this.ssid = ssid;
    }
    public String getBssid() {
        return bssid;
    }
    public void setBssid(String bssid) {
        this.bssid = bssid;
    }
    public Double getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }
    public Boolean getInsideOffice() {
        return insideOffice;
    }
    public void setInsideOffice(Boolean insideOffice) {
        this.insideOffice = insideOffice;
    }

    // TODO: Right-click in VS Code -> Source Action -> Generate Getters and Setters
}