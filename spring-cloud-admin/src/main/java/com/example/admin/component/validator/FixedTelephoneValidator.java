package com.example.admin.component.validator;

import com.example.admin.component.validator.annotation.FixedTelephone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class FixedTelephoneValidator implements ConstraintValidator<FixedTelephone, String> {

    private static final Pattern pattern = Pattern.compile(
            "^0[\\d]{2,3}-[\\d]{7,8}$");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return pattern.matcher(s).matches();
    }
}
