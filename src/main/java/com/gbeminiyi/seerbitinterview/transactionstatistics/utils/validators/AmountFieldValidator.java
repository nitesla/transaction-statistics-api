package com.gbeminiyi.seerbitinterview.transactionstatistics.utils.validators;

import com.gbeminiyi.seerbitinterview.transactionstatistics.utils.customAnnotations.AmountField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

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
