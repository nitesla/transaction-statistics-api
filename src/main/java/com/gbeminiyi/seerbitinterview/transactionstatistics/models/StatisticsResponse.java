package com.gbeminiyi.seerbitinterview.transactionstatistics.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponse {

  @Builder.Default
  private String sum = "0.00";

  @Builder.Default
  private String avg = "0.00";

  @Builder.Default
  private String max = "0.00";

  @Builder.Default
  private String min = "0.00";

  @Builder.Default
  private int count = 0;
}
