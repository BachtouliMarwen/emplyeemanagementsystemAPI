package com.tekup.staffmanagementapi.leave;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hr/leave-requests")
public class HRLeaveController {

    private final LeaveService leaveRequestService;

    @GetMapping
    public ResponseEntity<List<Leave>> getAllLeaves() {
        return ResponseEntity.ok(leaveRequestService.getAllLeaveRequests());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Leave>> getPendingLeaveRequests() {
        return ResponseEntity.ok(leaveRequestService.getPendingLeaveRequests());
    }

    @PatchMapping("/{leaveRequestId}/status")
    public ResponseEntity<Leave> updateLeaveRequestStatus(
            @PathVariable Long leaveRequestId,
            @RequestParam LeaveStatus status){
        return ResponseEntity.ok(leaveRequestService.updateLeaveRequestStatus(leaveRequestId, status));

    }
}
