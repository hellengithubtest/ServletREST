package com.servlet.app.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AdulthoodConstraintValidator.class)
@Target(value= ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Adulthood {
    String message() default "some message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
