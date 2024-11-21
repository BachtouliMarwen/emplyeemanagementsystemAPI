package com.tekup.staffmanagementapi.evaluation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee/evaluations")
public class EmployeeEvaluationController {

    private final EvaluationService evaluationService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Evaluation>> getEmployeeEvaluations(@PathVariable long employeeId) {
        return ResponseEntity.ok(evaluationService.getEmployeeEvaluations(employeeId));
    }

}
