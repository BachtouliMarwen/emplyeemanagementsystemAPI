package com.tekup.staffmanagementapi.evaluation;


import com.tekup.staffmanagementapi.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

    @Min(0)
    @Max(5)
    private int rating;

    @NotBlank
    private String feedback;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime UpdatedAt;

    @ManyToOne
    @JoinColumn(name= "employee_id", nullable = false)
    private User employee;

    @ManyToOne
    @JoinColumn(name = "evaluator_id", nullable = false)
    private User evaluator;
}
