package com.tekup.staffmanagementapi.timesheet;

import java.util.List;

public interface TimeSheetService {

    TimeSheet createTimeSheet(TimeSheet timeSheet);
    List<TimeSheet> getEmployeeTimesheets(Long employeeId);
    List<TimeSheet> getAllTimeSheets();
    List<TimeSheet> getPendingTimeSheets();
    TimeSheet updateStatus(Long timeSheetId, TimeSheetStatus status);

}
