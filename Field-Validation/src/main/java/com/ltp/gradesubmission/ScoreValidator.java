package com.ltp.gradesubmission;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScoreValidator implements ConstraintValidator<Score, String>{

    List<String> validScores = Arrays.asList("A", "B", "C", "D", "F");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String string : validScores) {
            if (value.equals(string)) {
                return true;
            }
            
        }



        return false;
    }

}
