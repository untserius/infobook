package com.serius.infobook.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GmailDomainValidator.class)
public @interface OnlyGmail {
    String message() default "Only Gmail address are allowed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
