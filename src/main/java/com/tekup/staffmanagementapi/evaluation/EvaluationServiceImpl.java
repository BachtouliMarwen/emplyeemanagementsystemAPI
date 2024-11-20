package com.tekup.staffmanagementapi.evaluation;

import com.tekup.staffmanagementapi.user.Role;
import com.tekup.staffmanagementapi.user.User;
import com.tekup.staffmanagementapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    @Override
    public Evaluation createEvaluation(Evaluation evaluation) {
        User evaluator = userRepository.findById(evaluation.getEvaluator().getId())
                .orElseThrow(() -> new RuntimeException("Evalutor Not Found"));

        if (!evaluator.getRole().equals(Role.HR)){
            throw new IllegalArgumentException("Only HR can create performance evaluations");
        }

        User employee = userRepository.findById(evaluation.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));

        if (evaluation.getRating() == 0 || (evaluation.getRating() < 0) || evaluation.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }

        evaluation.setEvaluationDate(LocalDateTime.now());
        evaluation.setUpdatedAt(LocalDateTime.now());
        return evaluationRepository.save(evaluation);
    }

    @Override
    public List<Evaluation> getEmployeeEvaluations(Long employeeId) {
        return evaluationRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Evaluation> getEvaluatorEvaluations(Long evaluatorId) {
        return evaluationRepository.findByEvaluatorId(evaluatorId);
    }
}
