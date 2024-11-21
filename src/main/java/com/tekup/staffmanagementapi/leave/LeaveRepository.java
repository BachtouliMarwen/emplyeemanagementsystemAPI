package com.tekup.staffmanagementapi.leave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findByEmployeeId(long id);
    List<Leave> findByStatus(LeaveStatus status);
}
