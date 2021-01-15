package com.example.admin.component.validator.annotation;

import com.example.admin.component.validator.ChineseCharacterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Constraint(validatedBy = {ChineseCharacterValidator.class})
public @interface ChineseCharacter {

    String message() default "必须是汉字";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
