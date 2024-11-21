package com.tekup.staffmanagementapi.timesheet;


import com.tekup.staffmanagementapi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="timesheet")
public class TimeSheet {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;

    private Date date;
    private Double hoursWorked;

    @Enumerated(EnumType.STRING)
    private TimeSheetStatus status;
}
