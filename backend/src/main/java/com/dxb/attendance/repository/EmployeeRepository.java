package com.dxb.attendance.repository;

import com.dxb.attendance.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Custom query to find an employee by their device hardware ID
    Employee findByRegisteredDevice(String registeredDevice);
}