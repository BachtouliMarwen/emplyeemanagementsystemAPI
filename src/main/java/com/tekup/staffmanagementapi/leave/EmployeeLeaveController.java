package com.tekup.staffmanagementapi.leave;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employee/leave-requests")
@RequiredArgsConstructor
public class EmployeeLeaveController {

    private final LeaveService leaveRequestService;

    @PostMapping
    public ResponseEntity<Leave> createLeaveRequest(
            @RequestParam("leaveRequest") String leaveRequestJson,
            @RequestParam(value = "document", required = false) MultipartFile document
    ) throws IOException{
        ObjectMapper mapper=new ObjectMapper();
        Leave leaveRequest=mapper.readValue(leaveRequestJson, Leave.class);
        return ResponseEntity.ok(leaveRequestService.createLeaveRequest(leaveRequest, document));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Leave>> getEmployeeLeaveRequests(@PathVariable long employeeId) {
        return ResponseEntity.ok(leaveRequestService.getEmployeeLeaveRequests(employeeId));
    }

}
