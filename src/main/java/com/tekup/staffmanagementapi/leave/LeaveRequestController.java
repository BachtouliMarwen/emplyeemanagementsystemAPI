package com.tekup.staffmanagementapi.leave;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<LeaveRequest> createLeaveRequest(
            @RequestParam("leaveRequest") String leaveRequestJson,
            @RequestParam(value = "document", required = false) MultipartFile document
    ) throws IOException{
        ObjectMapper mapper=new ObjectMapper();
        LeaveRequest leaveRequest=mapper.readValue(leaveRequestJson, LeaveRequest.class);
        return ResponseEntity.ok(leaveRequestService.createLeaveRequest(leaveRequest, document));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getEmployeeLeaveRequests(@PathVariable long employeeId) {
        return ResponseEntity.ok(leaveRequestService.getEmployeeLeaveRequests(employeeId));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<LeaveRequest>> getPendingLeaveRequests() {
        return ResponseEntity.ok(leaveRequestService.getPendingLeaveRequests());
    }

    @PatchMapping("/{leaveRequestId}/status")
    public ResponseEntity<LeaveRequest> updateLeaveRequestStatus(
            @PathVariable Long leaveRequestId,
            @RequestParam LeaveStatus status){
        return ResponseEntity.ok(leaveRequestService.updateLeaveRequestStatus(leaveRequestId, status));

    }
}
