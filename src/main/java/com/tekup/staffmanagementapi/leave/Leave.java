package com.tekup.staffmanagementapi.leave;


import com.tekup.staffmanagementapi.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


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
    @JoinColumn(name="user_id", nullable=false)
    private User employee;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LeaveType type;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    private String reason;

    @Lob
    private byte[] document;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;


}
