package com.tekup.staffmanagementapi.evaluation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        return ResponseEntity.ok(evaluationService.createEvaluation(evaluation));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Evaluation>> getEmployeeEvaluations(@PathVariable long employeeId) {
        return ResponseEntity.ok(evaluationService.getEmployeeEvaluations(employeeId));
    }

    @GetMapping("/evaluator/{evaluatorId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByEvaluator(@PathVariable long evaluatorId) {
        return ResponseEntity.ok(evaluationService.getEvaluatorEvaluations(evaluatorId));
    }
}
