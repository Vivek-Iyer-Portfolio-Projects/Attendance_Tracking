package com.dxb.attendance.repository;

import com.dxb.attendance.model.LocationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationLogRepository extends JpaRepository<LocationLog, Long> {
}