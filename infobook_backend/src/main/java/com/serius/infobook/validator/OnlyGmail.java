package com.serius.infobook.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // indicate that this annotation can only be applied to fields.
@Retention(RetentionPolicy.RUNTIME) //  ensures that Spring's validation framework can discover and apply these custom validation constraints dynamically at runtime
@Constraint(validatedBy = GmailDomainValidator.class) // to link this annotation with our custom validator class
public @interface OnlyGmail {
    String message() default "Only Gmail address are allowed.";
    
    // The groups and payload elements are attributes of constraint annotations.
    // By default, a constraint belongs to the default group, represented by an empty array {}.
    Class<?>[] groups() default {};

    // They provide additional metadata for customizing the behavior of validation constraints.
    // By default, no payloads are attached to a constraint, represented by an empty array {}.
    Class<? extends Payload>[] payload() default {};
}
