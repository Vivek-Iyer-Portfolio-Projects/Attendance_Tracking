# Multi-Factor Location Attendance System

## 📌 Project Overview
This project is an enterprise-grade employee attendance tracking system designed to replace vulnerable single-method tracking (like basic GPS or Wi-Fi only) with a robust, multi-factor location verification model. 

Designed with a security-first mindset, the system validates clock-in attempts by cross-referencing designated office Wi-Fi networks (SSID/BSSID) and utilizing the Haversine formula to enforce a strict 100-meter GPS geofence radius.

**Author:** Vivek Gopalkrishna Iyer

## 🏗️ System Architecture (Monorepo)
This repository is structured as a full-stack monorepo, housing both the backend API and the native mobile frontend.

*   **Backend:** Java 17, Spring Boot 3, Spring Data JPA
*   **Database (DEV Environment):** PostgreSQL (Relational persistence)
*   **Frontend (Mobile):** React Native (Community CLI), TypeScript
*   **Native Modules:** iOS CocoaPods (`netinfo`, `geolocation`)
*   **CI/CD:** GitHub Actions

## 🚀 Current Progress

### Phase 1: Backend API (Complete)
*   **Enterprise Persistence:** Transitioned from H2 in-memory to a full PostgreSQL relational database, establishing strict foreign key constraints between Employee and Attendance entities.
*   **Database Entities & Schema:** Modeled and mapped `Employees`, `Attendance`, and `Location_Logs` tables using Hibernate JPA.
*   **Core Verification Logic:** Engineered the `AttendanceVerificationService` to calculate true spherical distance across the Earth's surface using the Haversine formula.
*   **Monorepo Optimization:** Reconfigured Tomcat server to listen on port `8082` to eliminate architecture collisions with frontend node environments.
*   **DevSecOps Pipeline:** Established a GitHub Actions YAML workflow to automate Maven builds on every push.

### Phase 2: Mobile Application (Complete)
*   **Hardware Integration:** Linked native iOS hardware modules to extract real-time Wi-Fi state and high-accuracy GPS coordinates via custom TypeScript hooks.
*   **Dynamic User Interface:** Built a state-driven enterprise UI featuring conditional rendering, disabling actions until secure hardware checks pass.
*   **Network Layer:** Implemented a robust API service to transmit the hardware payload to the Spring Boot backend, elegantly handling both JSON and plain-text HTTP responses.
*   **Environment Configuration:** Successfully managed complex macOS environment paths, upgrading the system Ruby environment via Homebrew to compile CocoaPods and native Apple frameworks.

### Phase 3: Manager Dashboard (Upcoming)
*   **Tech Stack:** Angular, TypeScript, TailwindCSS
*   **Objective:** Develop a secure administrative web portal displaying real-time attendance summaries, audit logs, and compliance analytics.

## 🛠️ Prerequisites
Before running the application, ensure your environment has the following installed:
*   **Java Development Kit (JDK):** Version 17 or higher
*   **Node.js:** Version 18+ (LTS recommended)
*   **Xcode:** Full installation from the Mac App Store (required for Apple system frameworks)
*   **CocoaPods:** Native dependency manager for iOS (`sudo gem install cocoapods` or via Homebrew)
*   **PostgreSQL:** Version 14 or higher (`brew install postgresql@14` on macOS)

## ⚙️ How to Run Locally

### 1. Setup the Database
1. Start the PostgreSQL service: `brew services start postgresql@14`
2. Create the local database: `createdb attendancedb`
3. Ensure a test employee exists (e.g., ID `1`) in the `employees` table.

### 2. Start the Backend API
1. Navigate to the root directory, then run:
   ```bash
   cd backend
   ./mvnw clean spring-boot:run