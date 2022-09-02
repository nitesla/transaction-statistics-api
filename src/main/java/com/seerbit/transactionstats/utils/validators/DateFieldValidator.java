package com.seerbit.transactionstats.utils.validators;

import java.time.LocalDateTime;

import com.seerbit.transactionstats.utils.HelperUtils;
import com.seerbit.transactionstats.utils.customAnnotations.DateField;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateFieldValidator
  implements ConstraintValidator<DateField, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      LocalDateTime tnxTime = HelperUtils.convertToLocalDateTime(value);
      return tnxTime.isBefore(LocalDateTime.now());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
