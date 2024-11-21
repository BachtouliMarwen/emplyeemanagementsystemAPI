package com.tekup.staffmanagementapi.timesheet;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee/timesheet")
public class EmployeeTimeSheetController {

    private final TimeSheetService timeSheetService;

    @PostMapping
    public ResponseEntity<TimeSheet> createTimeSheet(@RequestBody TimeSheet timeSheet) {
        return ResponseEntity.ok(timeSheetService.createTimeSheet(timeSheet));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<TimeSheet> getTimeSheet(@PathVariable long employeeId) {
        return ResponseEntity.ok((TimeSheet) timeSheetService.getEmployeeTimesheets(employeeId));
    }
}
