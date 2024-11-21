package com.tekup.staffmanagementapi.timesheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {
    List<TimeSheet> findByEmployeeId(long employeeId);
    List<TimeSheet> findByStatus(TimeSheetStatus status);
}
