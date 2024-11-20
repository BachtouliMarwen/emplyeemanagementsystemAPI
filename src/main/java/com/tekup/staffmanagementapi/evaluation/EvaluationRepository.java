package com.tekup.staffmanagementapi.evaluation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByEmployeeId(long employeeId);
    List<Evaluation> findByEvaluatorId(long evaluatorId);
}
