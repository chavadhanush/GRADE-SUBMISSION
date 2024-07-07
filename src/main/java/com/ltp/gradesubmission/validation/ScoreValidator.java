package com.ltp.gradesubmission.validation;

import jakarta.validation.*;

import java.util.*;

public class ScoreValidator implements ConstraintValidator<Score, String> {

    List<String> scores = Arrays.asList(
            "A+", "A", "A-",
            "B+", "B", "B-",
            "C+", "C", "C-",
            "D+", "D", "D-",
            "F"
    );
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) return false;
        for(String string : scores) {
            if(value.equals(string)) return true;
        }
        return false;
    }
}
