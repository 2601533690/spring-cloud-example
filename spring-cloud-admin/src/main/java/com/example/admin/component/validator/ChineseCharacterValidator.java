package com.example.admin.component.validator;

import com.example.admin.common.util.StringUtil;
import com.example.admin.component.validator.annotation.ChineseCharacter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChineseCharacterValidator implements ConstraintValidator<ChineseCharacter, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtil.isOnlyChineseCharacter(s);
    }
}
