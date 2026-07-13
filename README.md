# Multi-Factor Location Attendance System

## 📌 Project Overview
This project is an enterprise-grade employee attendance tracking system designed to replace vulnerable single-method tracking (like basic GPS or Wi-Fi only) with a robust, multi-factor location verification model. 

Designed with a security-first mindset, the system validates clock-in attempts by cross-referencing designated office Wi-Fi networks (SSID/BSSID) and utilizing the Haversine formula to enforce a strict 100-meter GPS geofence radius.

**Author:** Vivek Gopalkrishna Iyer

## 🏗️ System Architecture
This repository is structured as a full-stack application, currently featuring a completed backend API. 

*   **Backend:** Java 17, Spring Boot 3, Spring Data JPA
*   **Database (DEV Environment):** H2 In-Memory Database (Zero-install, auto-seeded)
*   **Build Tool:** Maven
*   **CI/CD:** GitHub Actions

## 🚀 Current Progress: Phase 1 (Backend API Complete)
The foundational Spring Boot REST API has been successfully built, compiled, and tested.

*   **Database Entities & Schema:** Modeled and mapped `Employees`, `Attendance`, and `Location_Logs` tables.
*   **Automated Seeding:** Implemented `data.sql` to automatically populate the DEV environment with test data upon initialization.
*   **Core Verification Logic:** Engineered the `AttendanceVerificationService` to calculate true spherical distance across the Earth's surface using the Haversine formula, ensuring accurate 100m geofence validation.
*   **RESTful Endpoints:** Configured `AttendanceController` to serve backend data, currently exposing the `/api/attendance/employees` GET endpoint.
*   **DevSecOps Pipeline:** Established a GitHub Actions YAML workflow to automate Maven builds and verify code integrity on every push to the `main` branch.

## 🛠️ How to Run Locally
1. Ensure Java 17+ is installed.
2. Clone this repository.
3. Navigate to the `/backend` directory.
4. Run the following command:
   ```bash
   ./mvnw clean spring-boot:run