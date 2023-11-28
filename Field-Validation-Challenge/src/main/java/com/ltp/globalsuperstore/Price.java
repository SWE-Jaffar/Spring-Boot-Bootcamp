package com.ltp.globalsuperstore;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;

import java.lang.annotation.RetentionPolicy;



@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriceValidator.class)
public @interface Price {

    //default message if constraint is violated
    String message() default "Price cannot be less than discount";
    //boilerplate parameters.
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
