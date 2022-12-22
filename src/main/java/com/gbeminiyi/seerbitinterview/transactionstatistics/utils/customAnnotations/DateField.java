package com.gbeminiyi.seerbitinterview.transactionstatistics.utils.customAnnotations;

import com.gbeminiyi.seerbitinterview.transactionstatistics.utils.validators.DateFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFieldValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateField {
  String message() default "";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
