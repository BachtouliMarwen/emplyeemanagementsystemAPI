package com.tekup.staffmanagementapi.leave;


import com.tekup.staffmanagementapi.user.Role;
import com.tekup.staffmanagementapi.user.User;
import com.tekup.staffmanagementapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRequestRepository;
    private final UserRepository userRepository;

    @Override
    public Leave createLeaveRequest(Leave leaveRequest, MultipartFile document)
            throws IOException {
        User employee = userRepository.findById(leaveRequest.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (!employee.getRole().equals(Role.EMPLOYEE)){
            throw new RuntimeException("Only employees can submit leave requests.");
        }

        leaveRequest.setType(leaveRequest.getType());
        leaveRequest.setStatus(LeaveStatus.PENDING);
        leaveRequest.setStartDate(leaveRequest.getStartDate());
        leaveRequest.setEndDate(leaveRequest.getEndDate());

        if(document != null && document.isEmpty()){
            leaveRequest.setDocument(document.getBytes());
        }

        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public List<Leave> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public List<Leave> getEmployeeLeaveRequests(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Leave> getPendingLeaveRequests() {
        return leaveRequestRepository.findByStatus(LeaveStatus.PENDING);
    }

    @Override
    public Leave updateLeaveRequestStatus(Long leaveRequestId, LeaveStatus status) {
        Leave leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        if (!status.equals(LeaveStatus.APPROVED) && !status.equals(LeaveStatus.REJECTED)) {
            throw new IllegalArgumentException("Invalid leave status");
        }

        leaveRequest.setStatus(status);
        leaveRequest.setUpdatedAt(LocalDateTime.now());
        return leaveRequestRepository.save(leaveRequest);

    }
}
