package com.ninocorp.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingPageConstraintValidator.class)
public @interface ExistingPage {

    String message() default "There is no page with the given name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
