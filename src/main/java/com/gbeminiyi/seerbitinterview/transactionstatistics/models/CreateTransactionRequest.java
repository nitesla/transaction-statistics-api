package com.gbeminiyi.seerbitinterview.transactionstatistics.models;

import com.gbeminiyi.seerbitinterview.transactionstatistics.utils.customAnnotations.AmountField;
import com.gbeminiyi.seerbitinterview.transactionstatistics.utils.customAnnotations.DateField;
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
