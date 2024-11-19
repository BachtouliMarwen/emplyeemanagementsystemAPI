package com.tekup.staffmanagementapi.leave;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestService extends JpaRepository<LeaveRequest, Long> {

}
