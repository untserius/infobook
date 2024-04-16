package com.serius.infobook.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Custom Validator class to provide the logic of how validation will occur

/* We implement ConstraintValidator<OnlyGmail, String> interface where

    OnlyGmail - name of custom annotation.
    String - type of field we're validating.

*/
public class GmailDomainValidator implements ConstraintValidator<OnlyGmail, String> {

    /*
    This method is part of the ConstraintValidator interface and is called during the
    initialization of the validator.

    It allows you to perform any initialization logic if needed.
    In this case, the method body is empty because no initialization logic is required.
    */
    @Override
    public void initialize(OnlyGmail annotation){

    }

    // isValid method, checks the logic
    @Override
    //String email - the value of the field being validated
    // ConstraintValidatorContext context - provides contextual information and operations for constraint validation.
    public boolean isValid(String email, ConstraintValidatorContext context){
        // check if the email address ends with "gmail.com"
        return email != null && email.endsWith("gmail.com");
    }
}
