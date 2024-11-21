package com.tekup.staffmanagementapi.common;

import com.tekup.staffmanagementapi.leave.Leave;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LeaveDatesValidator implements ConstraintValidator<ValidLeaveDates, Leave> {

    @Override
    public boolean isValid(Leave leave, ConstraintValidatorContext constraintValidatorContext) {
        if (leave.getStartDate() == null || leave.getEndDate() == null) {
            return false;
        }
        return leave.getStartDate().isBefore(leave.getEndDate());
    }
}
