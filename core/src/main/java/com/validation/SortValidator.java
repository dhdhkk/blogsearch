package com.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class SortValidator implements ConstraintValidator<SortValid, String> {
    @Override
    public void initialize(SortValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        List<String> list = new ArrayList<>();
        list.add("accuracy");
        list.add("recency");

        System.out.println("list = " + list);
        for (String s : list) {
            if(s.equals(value)){
                return true;
            }
        }
        return false;
    }
}