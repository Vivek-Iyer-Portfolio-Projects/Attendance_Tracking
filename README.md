# Multi-Factor Location Attendance System

## 📌 Project Overview
This project is an enterprise-grade employee attendance tracking system designed to replace vulnerable single-method tracking (like basic GPS or Wi-Fi only) with a robust, multi-factor location verification model. 

Designed with a security-first mindset, the system validates clock-in attempts by cross-referencing designated office Wi-Fi networks (SSID/BSSID) and utilizing the Haversine formula to enforce a strict 100-meter GPS geofence radius.

**Author:** Vivek Gopalkrishna Iyer

## 🏗️ System Architecture (Monorepo)
This repository is structured as a full-stack monorepo, housing both the backend API and the native mobile frontend.

*   **Backend:** Java 17, Spring Boot 3, Spring Data JPA
*   **Database (DEV Environment):** H2 In-Memory Database (Zero-install, auto-seeded)
*   **Frontend (Mobile):** React Native (Community CLI), TypeScript
*   **Native Modules:** iOS CocoaPods (`netinfo`, `geolocation`)
*   **CI/CD:** GitHub Actions

## 🚀 Current Progress

### Phase 1: Backend API (Complete)
*   **Database Entities & Schema:** Modeled and mapped `Employees`, `Attendance`, and `Location_Logs` tables.
*   **Automated Seeding:** Implemented `data.sql` to auto-populate the DEV environment with test data upon initialization.
*   **Core Verification Logic:** Engineered the `AttendanceVerificationService` to calculate true spherical distance across the Earth's surface using the Haversine formula, ensuring accurate geofence validation.
*   **RESTful Endpoints:** Configured `AttendanceController` to serve backend data (`/api/attendance/employees`).
*   **DevSecOps Pipeline:** Established a GitHub Actions YAML workflow to automate Maven builds on every push to the `main` branch.

### Phase 2: Mobile Application (In Progress)
*   **Framework Initialization:** Scaffolded a modern React Native application.
*   **Hardware Integration:** Linked native iOS hardware modules (`@react-native-community/netinfo` and `@react-native-community/geolocation`) to extract real-time Wi-Fi state and GPS coordinates.
*   **Environment Configuration:** Successfully managed complex macOS environment paths, upgrading the system Ruby environment via Homebrew to compile CocoaPods and native Apple frameworks.
*   **Monorepo Restructuring:** Resolved nested Git repository conflicts to maintain a single, clean version control timeline across the entire stack.

## 🛠️ How to Run Locally

### 1. Start the Backend API
1. Ensure Java 17+ is installed.
2. Navigate to the root directory, then run:
   ```bash
   cd backend
   ./mvnw clean spring-boot:run