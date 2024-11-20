package com.tekup.staffmanagementapi.evaluation;


import java.util.List;

public interface EvaluationService {

    Evaluation createEvaluation(Evaluation evaluation);
    List<Evaluation> getEmployeeEvaluations(Long employeeId);
    List<Evaluation> getEvaluatorEvaluations(Long evaluatorId);

}
