package com.gbeminiyi.seerbitinterview.transactionstatistics.utils.validators;

import com.gbeminiyi.seerbitinterview.transactionstatistics.utils.HelperUtils;
import com.gbeminiyi.seerbitinterview.transactionstatistics.utils.customAnnotations.DateField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

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
