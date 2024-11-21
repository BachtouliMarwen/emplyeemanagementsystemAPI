package com.tekup.staffmanagementapi.timesheet;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tekup.staffmanagementapi.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @DecimalMin(value = "0.0", message = "Hours worked cannot be negative")
    @DecimalMax(value = "24.0", message = "Hours worked cannot exceed 24 hours")
    private Double hoursWorked;

    @Enumerated(EnumType.STRING)
    private TimeSheetStatus status = TimeSheetStatus.PENDING;

    @JsonIgnore
    public User getEmployee() {
        return employee;
    }

}
