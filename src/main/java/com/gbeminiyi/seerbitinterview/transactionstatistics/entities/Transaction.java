package com.gbeminiyi.seerbitinterview.transactionstatistics.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
  public BigDecimal amount;
  public String timestamp;
}
