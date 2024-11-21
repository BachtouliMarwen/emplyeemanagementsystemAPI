package com.tekup.staffmanagementapi.leave;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LeaveService {

    Leave createLeaveRequest(Leave leaveRequest, MultipartFile document) throws IOException;
    List<Leave> getAllLeaveRequests();
    List<Leave> getEmployeeLeaveRequests(Long employeeId);
    List<Leave> getPendingLeaveRequests();
    Leave updateLeaveRequestStatus(Long leaveRequestId, LeaveStatus status);

}
