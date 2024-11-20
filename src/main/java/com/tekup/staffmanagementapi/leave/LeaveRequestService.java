package com.tekup.staffmanagementapi.leave;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LeaveRequestService {

    LeaveRequest createLeaveRequest(LeaveRequest leaveRequest, MultipartFile document) throws IOException;
    List<LeaveRequest> getEmployeeLeaveRequests(Long employeeId);
    List<LeaveRequest> getPendingLeaveRequests();
    LeaveRequest updateLeaveRequestStatus(Long leaveRequestId, LeaveStatus status);

}
