package com.seerbit.transactionstats.utils.validators;

import java.util.regex.Pattern;

import com.seerbit.transactionstats.utils.customAnnotations.AmountField;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AmountFieldValidator
  implements ConstraintValidator<AmountField, String> {

  Pattern pattern = Pattern.compile("^[\\d]+[\\.]{0,1}[\\d]*");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      return (pattern.matcher(value).matches());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
