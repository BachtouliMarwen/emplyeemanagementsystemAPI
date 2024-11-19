package com.tekup.staffmanagementapi.evaluation;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name="evaluation")
public class PerformanceEvaluation {

    @Id
    @GeneratedValue
    private Long id;

    private Date evaluationDate;
    private Double score;
    private String comments;
}
