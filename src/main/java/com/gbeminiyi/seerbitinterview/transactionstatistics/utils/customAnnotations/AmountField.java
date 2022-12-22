package com.gbeminiyi.seerbitinterview.transactionstatistics.utils.customAnnotations;

import com.gbeminiyi.seerbitinterview.transactionstatistics.utils.validators.AmountFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AmountFieldValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AmountField {
	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
