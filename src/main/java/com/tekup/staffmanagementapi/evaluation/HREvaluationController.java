package com.tekup.staffmanagementapi.evaluation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr/evaluations")
@RequiredArgsConstructor
public class HREvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        return ResponseEntity.ok(evaluationService.createEvaluation(evaluation));
    }

    @GetMapping("/{evaluatorId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByEvaluator(@PathVariable long evaluatorId) {
        return ResponseEntity.ok(evaluationService.getEvaluatorEvaluations(evaluatorId));
    }
}
