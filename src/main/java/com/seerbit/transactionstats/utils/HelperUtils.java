package com.seerbit.transactionstats.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelperUtils {

  public static LocalDateTime convertToLocalDateTime(String time) {
    Instant instant = Instant.parse(time);
    return LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
  }

  public static String roundAmount(Double bigDecimal) {
    try {
      return new BigDecimal(bigDecimal)
        .setScale(2, RoundingMode.HALF_UP)
        .toPlainString();
    } catch (Exception e) {
      log.info("ERROR WHILE ROUNDING AMOUNT:::" + e.getLocalizedMessage());
      return new BigDecimal(bigDecimal).toPlainString();
    }
  }
}
