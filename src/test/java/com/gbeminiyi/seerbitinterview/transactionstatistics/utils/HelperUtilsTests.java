package com.gbeminiyi.seerbitinterview.transactionstatistics.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelperUtilsTests {

  @Test
  public void testStringToLocalDateTimeFormat() {
    String time = "2022-12-22T09:59:51.312Z";

    LocalDateTime localDateTime = HelperUtils.convertToLocalDateTime(time);

    assertEquals(localDateTime.toLocalDate(), LocalDate.now());
  }
}
