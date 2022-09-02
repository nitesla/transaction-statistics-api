package com.seerbit.transactionstats.models;

import com.seerbit.transactionstats.utils.customAnnotations.AmountField;
import com.seerbit.transactionstats.utils.customAnnotations.DateField;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTransactionRequest {

  @AmountField
  private String amount;

  @DateField
  private String timestamp;
}
