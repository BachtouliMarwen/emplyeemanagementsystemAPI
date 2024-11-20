package com.tekup.staffmanagementapi.evaluation;


import com.tekup.staffmanagementapi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="evaluation")
public class Evaluation {

    @Id
    @GeneratedValue
    private Long id;

    private int rating;
    private String feedback;
    private LocalDateTime evaluationDate;
    private LocalDateTime UpdatedAt;

    @ManyToOne
    @JoinColumn(name= "employee_id", nullable = false)
    private User employee;

    @ManyToOne
    @JoinColumn(name = "evaluator_id", nullable = false)
    private User evaluator;
}
