package com.tekup.staffmanagementapi.timesheet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSheetServiceImpl implements TimeSheetService {

    private final TimeSheetRepository timeSheetRepository;

    @Override
    public TimeSheet createTimeSheet(TimeSheet timeSheet) {
        timeSheet.setStatus(TimeSheetStatus.PENDING);
        return timeSheetRepository.save(timeSheet);
    }

    @Override
    public List<TimeSheet> getEmployeeTimesheets(Long employeeId) {
        return timeSheetRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<TimeSheet> getAllTimeSheets() {
        return timeSheetRepository.findAll();
    }

    @Override
    public List<TimeSheet> getPendingTimeSheets() {
        return timeSheetRepository.findByStatus(TimeSheetStatus.PENDING);
    }

    @Override
    public TimeSheet updateStatus(Long timeSheetId, TimeSheetStatus status) {
        TimeSheet timeSheet = timeSheetRepository.findById(timeSheetId)
                .orElseThrow(()->new RuntimeException("TimeSheet not found"));
        timeSheet.setStatus(status);
        return timeSheetRepository.save(timeSheet);
    }
}
