package com.tekup.staffmanagementapi.timesheet;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hr/timesheets")
public class HRTimeSheetController {

    private final TimeSheetService timeSheetService;

    @GetMapping
    public ResponseEntity<List<TimeSheet>> getAllTimeSheets() {
        return ResponseEntity.ok(timeSheetService.getAllTimeSheets());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<TimeSheet>> getPendingTimeSheets() {
        return ResponseEntity.ok(timeSheetService.getPendingTimeSheets());
    }

    @PatchMapping("/{timeSheetId}/status")
    public ResponseEntity<TimeSheet> updateStatus(
            @PathVariable Long timeSheetId,
            @RequestBody TimeSheetStatus status) {
        return ResponseEntity.ok(timeSheetService.updateStatus(timeSheetId, status));
    }
}
