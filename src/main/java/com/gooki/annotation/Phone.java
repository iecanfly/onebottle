package com.gooki.annotation;

/**
 * Created by iecanfly on 2/21/15.
 */

import com.gooki.validation.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface Phone {
    String message() default "errors.phone";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}