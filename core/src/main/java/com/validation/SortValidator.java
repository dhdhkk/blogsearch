package com.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SortValidator implements ConstraintValidator<SortValid, String> {
    @Override
    public void initialize(SortValid constraintAnnotation) {
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("accuracy") || value.equals("recency");
    }
}