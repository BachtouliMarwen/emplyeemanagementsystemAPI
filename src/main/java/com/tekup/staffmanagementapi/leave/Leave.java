package com.tekup.staffmanagementapi.leave;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tekup.staffmanagementapi.common.ValidLeaveDates;
import com.tekup.staffmanagementapi.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ValidLeaveDates
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="leave_request")
public class Leave {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User employee;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LeaveType type;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status = LeaveStatus.PENDING;

    private String reason;

    @Lob
    private byte[] document;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @JsonIgnore
    public User getEmployee() {
        return employee;
    }
}