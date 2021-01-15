package com.example.admin.component.validator.annotation;

import com.example.admin.component.validator.FixedTelephoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Constraint(validatedBy = {FixedTelephoneValidator.class})
public @interface FixedTelephone {

    String message() default "固定电话格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
