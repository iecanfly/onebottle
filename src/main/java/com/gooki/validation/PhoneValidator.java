package com.gooki.validation;

import com.gooki.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by iecanfly on 2/21/15.
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone paramA) {
    }

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
        if (phoneNo == null) {
            return false;
        }
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{11}")) return true;
            //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{4}[-\\.\\s]\\d{4}")) return true;
        else return false;
    }



}
