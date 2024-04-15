package com.serius.infobook.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GmailDomainValidator implements ConstraintValidator<OnlyGmail, String> {

    @Override
    public void initialize(OnlyGmail annotation){

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        // check if the email address ends with "gmail.com"
        return email != null && email.endsWith("gmail.com");
    }
}
