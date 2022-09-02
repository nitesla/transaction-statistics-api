package com.seerbit.transactionstats.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class HelperUtilsTests {

  @Test
  public void testStringToLocalDateTimeFormat() {
    String time = "2022-09-02T09:59:51.312Z";

    LocalDateTime localDateTime = HelperUtils.convertToLocalDateTime(time);

    assertEquals(localDateTime.toLocalDate(), LocalDate.now());
  }
}
