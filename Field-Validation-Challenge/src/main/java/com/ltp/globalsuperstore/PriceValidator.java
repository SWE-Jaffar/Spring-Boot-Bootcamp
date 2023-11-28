package com.ltp.globalsuperstore;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<Price, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        // Check if value is null
        if (value == null) {
            return false; // Or return true based on your validation logic
        }

        Item currentItem = ValidationContext.getCurrentItem();
        
        // Check if currentItem is null
        if (currentItem == null) {
            return false; // Or handle differently based on your requirements
        }

        System.out.println(currentItem.getDiscount());
        System.out.println(value);

        return value >= currentItem.getDiscount();
    }
}
